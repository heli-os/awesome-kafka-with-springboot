package kr.dataportal.application.consumer.logger.aspect

import kr.dataportal.application.consumer.logger.ConsumedMessageLogger
import kr.dataportal.application.consumer.logger.model.ConsumeResultContext
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * @author Heli
 * Created on 2022. 08. 23
 */
@Component
@Aspect
class ConsumerLogTraceAspect(
    private val consumedMessageLogger: ConsumedMessageLogger
) {

    @Around("@annotation(kafkaListener)")
    fun logAround(pjp: ProceedingJoinPoint, kafkaListener: KafkaListener): Any? = runCatching {
        val proceedResult = pjp.proceed()
        consumedMessageLogger.log(pjp, proceedResult as? ConsumeResultContext)
    }.getOrElse {
        consumedMessageLogger.log(pjp, null)
    }
}
