package kr.dataportal.application.consumer.config

import kr.dataportal.application.kafkaCommonProperties
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

/**
 * @author Heli
 * Created on 2022. 08. 25
 */
@Configuration
@EnableConfigurationProperties(AwesomeKafkaConsumerProperties::class)
class KafkaConsumerConfig(
    private val awesomeKafkaConsumerProperties: AwesomeKafkaConsumerProperties
) {

    companion object {
        private const val CONCURRENCY = 1
    }

    @Bean
    fun paymentKafkaListenerContainerFactory() =
        ConcurrentKafkaListenerContainerFactory<String, Any>().apply {
            consumerFactory = paymentConsumerFactory()
            isBatchListener = false
            setConcurrency(CONCURRENCY)
        }

    private fun paymentConsumerFactory(): ConsumerFactory<String, Any> {
        val consumerConfig = mapOf(
            ConsumerConfig.GROUP_ID_CONFIG to awesomeKafkaConsumerProperties.consumer.groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to LongDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java
        )
        return DefaultKafkaConsumerFactory(consumerConfig + kafkaProperties())
    }

    private fun kafkaProperties() = kafkaCommonProperties(awesomeKafkaConsumerProperties.bootstrapServerHost)
}

