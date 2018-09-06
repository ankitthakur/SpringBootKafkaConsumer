package com.thakur.kafkaserver.model

import com.fasterxml.jackson.databind.JsonNode
import lombok.*

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
data class Person(
        var firstName: String,
        var lastName:String,
        var messageTime:Long
)