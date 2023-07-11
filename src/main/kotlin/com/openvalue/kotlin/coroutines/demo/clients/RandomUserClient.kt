package com.openvalue.kotlin.coroutines.demo.clients

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.RuntimeException

@Component
class RandomUserClient {

    private val client = WebClient.create("https://randomuser.me/api/")

    fun retrieveRandomUsers(): Flux<RandomUserResponse> {
        val retrieve = client.get()
            .uri { uriBuilder -> uriBuilder.queryParam("results", 10).build() }
            .accept(MediaType.APPLICATION_JSON).retrieve()

       return retrieve.onStatus(
           { status -> status.is2xxSuccessful }, { Mono.empty<RuntimeException>() }
        ).bodyToFlux(RandomUserResponse::class.java)
    }
}

data class RandomUserResponse(val results: List<User>)

data class User (
    val name: Name?,
    val location: Location?,
    val email: String?
)

data class Name(val title: String, val first: String, val last: String)
data class Location(val street: Street, val city: String, val state: String, val country: String, val postcode: String)
data class Street(val number: Int, val name: String)