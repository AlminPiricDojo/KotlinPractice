package com.example.kotlinpractice

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{

    @Test
    fun `empty user name returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "12",
            "12"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Bob",
            "12",
            "12"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Peter",
            "12",
            "12"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Ben",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `passwords do not match returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Joe",
            "12",
            "1e2"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password is too short returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Tod",
            "1",
            "1"
        )
        assertThat(result).isFalse()
    }

}