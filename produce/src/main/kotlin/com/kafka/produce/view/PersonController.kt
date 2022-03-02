package com.kafka.produce.view
import com.kafka.produce.dto.Person
import com.kafka.produce.produce.SendToKafka
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/person")
class PersonController(val sendToKafka: SendToKafka) {

    @Value("\${topic-person}")
    lateinit var topic : String

    @PostMapping("/post")
    fun post(@Valid @RequestBody person : Person) : ResponseEntity<Any> {
        return try {
            sendToKafka.sendToKafkaJson(topic, person)
            ResponseEntity.ok().body("Mensagem enviada com sucesso!")
        } catch (e : Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: ${e.message}")
        }
    }
}