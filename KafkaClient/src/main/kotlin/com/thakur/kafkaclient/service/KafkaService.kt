package com.thakur.kafkaclient.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.thakur.kafkaserver.model.Person
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaService{

    fun clientService(): String {
        return "hello from kafka client service"
    }


    private val logger: Logger = LoggerFactory.getLogger(KafkaService::class.java)

    @KafkaListener(topics = arrayOf("#{'\${spring.kafka.topic}'.split(',')}"), groupId = "#{'\${spring.kafka.topic.groupId}'}")
    fun recieveMessage2(record:ConsumerRecord<String, Any>) {

        val jsonString: String = String(record.value() as ByteArray)


        logger.info("Received person.topic: {}", record.topic())
        logger.info("Received person.partition: {}", record.partition())
        logger.info("Received person.offset: {}", record.offset())
        logger.info("json string: {}", jsonString)

        val objectMapper = ObjectMapper()

        val node: JsonNode = objectMapper.readTree(record.value() as ByteArray)
        logger.info("Node: {}", node)
        val person:Person = Person(firstName = node["firstName"].asText(), lastName = node["lastName"].asText(), messageTime = node["messageTime"].asLong())
        logger.info("Person: {}", person)
    }

}