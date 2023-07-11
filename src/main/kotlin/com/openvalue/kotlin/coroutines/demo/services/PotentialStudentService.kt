package com.openvalue.kotlin.coroutines.demo.services

import org.springframework.stereotype.Service

@Service
class PotentialStudentService {

    fun gatherInformation(): List<PotentialStudentValidation> {
        //TODO for a set of random users:
        // - retrieve whether there are universities in the country they live in
        // - Predict their age given the name
        // - validate the e-mail
        // ---> this all at the same time
        return emptyList()
    }
}

data class PotentialStudentValidation(
    val name: String,
    val predictedAge: Int,
    val universityInTheCountry: Boolean,
    val givenEmailAddress: String,
    val validEmail: Boolean
)