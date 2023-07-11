package com.openvalue.kotlin.coroutines.demo.clients

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class UniversityClient {

    private val client = WebClient.create("http://universities.hipolabs.com/search")

    fun retrieveUniversity(country: String): Flux<University> {
        val retrieve = client.get()
            .uri { uriBuilder -> uriBuilder.queryParam("country", country).build() }
            .accept(MediaType.APPLICATION_JSON).retrieve()

        return retrieve.onStatus(
            { status -> status.is2xxSuccessful }, { Mono.empty<RuntimeException>() }
        ).bodyToFlux(University::class.java)
    }
}

data class University(val country: String, val name: String)