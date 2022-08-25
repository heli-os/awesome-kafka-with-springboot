package kr.dataportal.application.consumer.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 * @author Heli
 * Created on 2022. 08. 25
 */
@ConstructorBinding
@ConfigurationProperties(prefix = "awesome-kafka")
class AwesomeKafkaConsumerProperties(
    val bootstrapServerHost: String,
    val consumer: ConsumerProperty
)

data class ConsumerProperty(
    val groupId: String,
    val enableAutoCommit: Boolean,
    val autoOffsetReset: String
)
