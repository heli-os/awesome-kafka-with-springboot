package kr.dataportal.application.config

import kr.dataportal.application.kafkaCommonProperties
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.KafkaAdmin

/**
 * @author Heli
 * Created on 2022. 08. 26
 */
@Configuration
@EnableKafka
class KafkaAdminConfig(
    @Value("\${awesome-kafka.bootstrap-server-host}") private val bootstrapServerHost: String,
    @Value("\${awesome-kafka.topics.payment}") private val paymentTopic: String
) {

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        return KafkaAdmin(kafkaCommonProperties(bootstrapServerHost))
    }

    @Bean
    fun paymentTopic(): NewTopic {
        return NewTopic(paymentTopic, 1, 1)
    }
}
