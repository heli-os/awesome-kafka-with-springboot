package kr.dataportal.application.consumer.logger.model

/**
 * @author Heli
 * Created on 2022. 08. 23
 */
data class ConsumeResultContext(
    val status: Status
) {

    enum class Status {
        SUCCESS, FAILURE
    }
}
