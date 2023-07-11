package com.openvalue.kotlin.coroutines.demo

import com.openvalue.kotlin.coroutines.demo.clients.AgePredictorClient
import com.openvalue.kotlin.coroutines.demo.clients.RandomUserClient
import com.openvalue.kotlin.coroutines.demo.clients.UniversityClient
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@Component
open class ApplicationDemoRunner(
    val randomUserClient: RandomUserClient, val universityClient: UniversityClient, val agePredictorClient: AgePredictorClient
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val retrieveRandomUsers = randomUserClient.retrieveRandomUsers().blockFirst()

        println("User: $retrieveRandomUsers")

        val country = "Brazil"

        val universities = universityClient.retrieveUniversity(country).blockFirst()

        println("Universities $country: $universities")

        val name = "Peter"

        val agePrediction = agePredictorClient.predictAge(name).block()

        println("Predicted age of $name: $agePrediction")
    }

}
