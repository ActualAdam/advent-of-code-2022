/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.actualadam.aoc2022

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class LibraryTest : StringSpec({
    "some library method returns 'true'" {
        val classUnderTest = Library()
        classUnderTest.someLibraryMethod() shouldBe true
    }
})