package com.actualadam.aoc2022.day02.iteration2

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day02.iteration1.Day02Part1
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day02Iteration2Test: StringSpec({
    val input = TestUtil.getInputString("/day02/part1-input.txt")

    "part 1" {
        Day02Iteration2.part1(input) shouldBe 11475
    }

    "part 2" {
        Day02Iteration2.part2(input) shouldBe 16862
    }

})
