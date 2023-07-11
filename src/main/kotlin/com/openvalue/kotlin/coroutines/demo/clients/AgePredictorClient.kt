package com.openvalue.kotlin.coroutines.demo.clients

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class AgePredictorClient {

    private val client = WebClient.create("https://api.agify.io/")

    fun predictAge(firstName: String): Mono<AgePrediction> {
        val retrieve = client.get()
            .uri { uriBuilder -> uriBuilder.queryParam("name", firstName).build() }
            .accept(MediaType.APPLICATION_JSON).retrieve()

        return retrieve.onStatus(
            { status -> status.is2xxSuccessful }, { Mono.empty<RuntimeException>() }
        ).bodyToMono(AgePrediction::class.java)
    }
}

data class AgePrediction(val name: String, val age: Int)