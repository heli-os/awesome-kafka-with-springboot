package kr.dataportal.application.consumer.logger.model

/**
 * @author Heli
 * Created on 2022. 08. 23
 */
data class ConsumeLog(
    val host: String,
    val listenerMethod: String,
    val traceId: String? = null,
    val message: String,
    val context: ConsumeResultContext? = null
)
