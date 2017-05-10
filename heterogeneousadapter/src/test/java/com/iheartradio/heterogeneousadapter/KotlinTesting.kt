package com.iheartradio.heterogeneousadapter

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

/**
 * Created by Jonathan Muller on 4/28/17.
 */
class KotlinTesting : Spek({

    describe("Calculator") {

        val calculator = Calculator()

        it ("should return the result of adding the first and second number") {
            val result = calculator.plus(1, 1)
            assertEquals(2, result)
        }

        it ("should return the result of subtracting the second number from the first") {
            val result = calculator.minus(2, 1)
            assertEquals(1, result)
        }
    }
})