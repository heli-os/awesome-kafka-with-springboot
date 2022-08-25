package kr.dataportal.application

import org.apache.kafka.clients.CommonClientConfigs

/**
 * @author Heli
 * Created on 2022. 08. 26
 */
fun kafkaCommonProperties(
    bootstrapServerHost: String
) = mapOf(
    CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG to bootstrapServerHost
)
