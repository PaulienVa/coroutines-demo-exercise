package com.openvalue.kotlin.coroutines.demo.validators

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EmailValidatorTest {

    @Test
    fun `valid email is validated`() {
        val validEmail = EmailValidator.isValidEmail("abc@xyz.nl")
        assertTrue(validEmail)
    }

    @Test
    fun `invalid email is NOT validated`() {
        val validEmail = EmailValidator.isValidEmail("abc#xyz.nl")
        assertFalse(validEmail)
    }
}