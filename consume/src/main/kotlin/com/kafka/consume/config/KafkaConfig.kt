package com.kafka.consume.config

import com.kafka.consume.dto.Person
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import java.util.*

@Configuration
@EnableKafka
class KafkaConfig {

    @Value("\${bootstrap.servers}")
    private lateinit var servers : String

    @Value("\${group-id}")
    private lateinit var groupId: String

    @Value("\${key.serializer}")
    private lateinit var keySerializer: String

    @Value("\${value.serializer}")
    private lateinit var valueSerializer: String

    private fun config() : Map<String, Any>{
        val map = HashMap<String, String>()
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers)
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keySerializer)
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueSerializer)
        map.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

        return map
    }

    private fun consumerFactory() : ConsumerFactory<String, Person> {
        return DefaultKafkaConsumerFactory(config(), StringDeserializer(), JsonDeserializer(Person::class.java))
    }

    @Bean
    fun kafkaListenerFactory() : ConcurrentKafkaListenerContainerFactory<String, Person> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Person>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}