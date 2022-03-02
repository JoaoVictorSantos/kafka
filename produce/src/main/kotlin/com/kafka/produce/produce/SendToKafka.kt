package com.kafka.produce.produce

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.kafka.produce.config.Config
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

interface SendToKafkaFacede {
    fun sendToKafkaJson(topic : String, obj : Any)
}

@Service
class SendToKafka(val kafkaConfig : Config) : SendToKafkaFacede {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger("ConsumerKafka")
    }

    @Autowired
    private lateinit var kafkaTemplate : KafkaTemplate<String, String>

    private val producerJson =  kafkaConfig.producerJson()

    override fun sendToKafkaJson(topic : String, obj : Any) {
        val node = ObjectMapper().valueToTree<JsonNode>(obj)
        val record : ProducerRecord<String, JsonNode> = ProducerRecord(topic, node)
        producerJson.send(record)

        LOGGER.info("Producer Person: ${obj.toString()}")
    }

}