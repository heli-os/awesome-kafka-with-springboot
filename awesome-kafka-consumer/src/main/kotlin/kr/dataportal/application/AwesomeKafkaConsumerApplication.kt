package kr.dataportal.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Heli
 * Created on 2022. 08. 21
 */
@SpringBootApplication
class AwesomeKafkaConsumerApplication

fun main(args: Array<String>) {
    runApplication<AwesomeKafkaConsumerApplication>(*args)
}
