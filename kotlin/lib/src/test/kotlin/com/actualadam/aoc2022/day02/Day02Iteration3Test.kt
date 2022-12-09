package com.actualadam.aoc2022.day02

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day02.iteration3.Day02Iteration3
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day02Iteration3Test : FreeSpec({
//    "part 1 example" {
//
//    }

    "part 1 puzzle input" {
        Day02Iteration3.part1(TestUtil.getInputLines("/day02/part1-input.txt")) shouldBe 11475
    }

//    "part 2 example" {
//
//    }

    "part 2 puzzle input" {
        Day02Iteration3.part2(TestUtil.getInputLines("/day02/part1-input.txt")) shouldBe 16862
    }
})
