package com.example.kotlinpractice

object RegistrationUtil{

    private val existingUsers = listOf("Peter", "Carl")

    /*
        * the input is not valid...
        * ...if the username/password is empty
        * ...the username is already taken
        * ...the passwords do not match
        * ...the password is too short (2 digits)
     */

    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean{
        return when{
            username.isEmpty() -> false
            username in existingUsers -> false
            password.isEmpty() -> false
            password != confirmPassword -> false
            password.length < 2 -> false
            else -> true
        }
    }
}