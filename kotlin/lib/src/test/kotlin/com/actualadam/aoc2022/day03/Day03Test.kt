package com.actualadam.aoc2022.day03

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day03.iteration1.Day03
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day03Test : StringSpec({
    "part 1 example" {
        Day03.part1(TestUtil.getInputLines("/day03/example.txt")) shouldBe 157
    }

    "part 1 puzzle input" {
        Day03.part1(TestUtil.getInputLines("/day03/puzzle-input.txt")) shouldBe 8176
    }

    "part 2 example" {
        Day03.part2(TestUtil.getInputLines("/day03/example.txt")) shouldBe 70
    }

    "part 2 puzzle input" {
        Day03.part2(TestUtil.getInputLines("/day03/puzzle-input.txt")) shouldBe 2689
    }
})
