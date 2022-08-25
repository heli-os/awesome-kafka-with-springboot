package kr.dataportal.application.consumer.listener

import kr.dataportal.application.ApplicationLogger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

/**
 * @author Heli
 * Created on 2022. 08. 26
 */
@Component
class PaymentListener {

    companion object : ApplicationLogger()

    @KafkaListener(
        topics = ["\${awesome-kafka.topics.payment}"],
        containerFactory = "paymentKafkaListenerContainerFactory"
    )
    fun listenPayment(@Payload message: String) {
        log.info(message)
    }
}
