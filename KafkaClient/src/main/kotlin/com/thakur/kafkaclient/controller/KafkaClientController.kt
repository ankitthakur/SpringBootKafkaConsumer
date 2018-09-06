package com.thakur.kafkaclient.controller

import com.thakur.kafkaclient.service.KafkaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("kafka")
class KafkaClientController {

    private val service:KafkaService

    constructor(service: KafkaService) {
        this.service = service
    }


    @GetMapping("client")
    fun helloClient(): String {
        return service.clientService()
    }
}