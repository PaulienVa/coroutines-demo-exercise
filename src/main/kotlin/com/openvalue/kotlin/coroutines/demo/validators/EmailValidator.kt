package com.openvalue.kotlin.coroutines.demo.validators

object EmailValidator {
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }
}