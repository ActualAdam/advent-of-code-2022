package com.actualadam.aoc2022.day02.iteration1

import com.actualadam.aoc2022.TestUtil
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day02Test : StringSpec({
    val exampleInput = """
            A Y
            B X
            C Z
        """.trimIndent()

    val puzzleInput = TestUtil.getInputString("/day02/part1-input.txt")

    "example input should equal example answer" {
        Day02Part1.part01(exampleInput) shouldBe 15
    }

    "part 1 input should produce an answer" {
        Day02Part1.part01(puzzleInput) shouldBe 11475
    }

    "part 2 example" {
        Day02Part2.part02(exampleInput) shouldBe 12
    }

    "part 2 puzzle input" {
        Day02Part2.part02(puzzleInput) shouldBe 16862
    }
})
