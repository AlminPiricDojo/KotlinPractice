package com.example.kotlinpractice

import org.junit.Assert.*
import org.junit.Test

class MainActivityTest{
    /**
     * Test changeText function
     * number could be null
     * number could be zero
     */
    private val mainActivity = MainActivity()

    @Test
    fun `number is null` () {
        assertThat(mainActivity.randNum > 0, null)
    }
}