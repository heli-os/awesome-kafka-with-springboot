package kr.dataportal.application.consumer.logger

import kr.dataportal.application.ApplicationLogger
import kr.dataportal.application.consumer.logger.model.ConsumeLog
import kr.dataportal.application.consumer.logger.model.ConsumeResultContext
import kr.dataportal.application.toJson
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.MDC
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.lang.reflect.Method
import java.net.InetAddress
import java.util.*

/**
 * @author Heli
 * Created on 2022. 08. 23
 */
@Component
class ConsumedMessageLogger {

    companion object : ApplicationLogger() {
        private const val LOG_TRACE_ID_KEY = "traceId"
    }

    private val host: String = runCatching {
        InetAddress.getLocalHost().hostAddress
    }.getOrDefault("UNKNOWN_HOST")

    fun log(pjp: ProceedingJoinPoint, resultContext: ConsumeResultContext?): String? {
        return runCatching {
            getMessagePayload(pjp)?.let { message ->
                ConsumeLog(
                    host = host,
                    listenerMethod = pjp.signature.toShortString(),
                    traceId = generateRequestId(),
                    message = message,
                    context = resultContext
                ).toJson().also { log.info(it) }
            }
        }.getOrDefault("").also {
            MDC.clear()
        }
    }

    private fun getMessagePayload(pjp: ProceedingJoinPoint): String? {
        val parameters = pjp.getParameterAnnotations()
        parameters.forEach {
            if (it.value.any { annotation -> Payload::class.java.isAssignableFrom(annotation.javaClass) }) {
                return pjp.args[it.index].toString()
            }
        }
        return null
    }

    private fun ProceedingJoinPoint.getMethod(): Method {
        val methodSignature = this.signature as MethodSignature
        return methodSignature.method
    }

    private fun ProceedingJoinPoint.getParameterAnnotations(): kotlin.collections.Iterator<IndexedValue<Array<Annotation>>> {
        return this.getMethod().parameterAnnotations.iterator().withIndex()
    }

    private fun generateRequestId(): String = runCatching {
        UUID.randomUUID().toString().substring(0, 8).apply {
            MDC.put(LOG_TRACE_ID_KEY, this)
        }
    }.getOrDefault("")
}
