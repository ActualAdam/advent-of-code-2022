package com.actualadam.aoc2022.day09

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day09.Day09Iteration2.part1
import com.actualadam.aoc2022.day09.Day09Iteration2.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day09Iteration2Test : FreeSpec({
    "Day 09" - {
        val exampleInput = TestUtil.getInputLines("/day09/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day09/puzzle-input.txt")
        "part 1" - {
            "example input" {
                part1(exampleInput) shouldBe 13
            }
            "puzzle input" {
                part1(puzzleInput) shouldBe 6057
            }
        }
        "part 2" - {
            "example input" {
                part2(exampleInput) shouldBe 1
            }
            "puzzle input" {
                part2(puzzleInput) shouldBe 2514
            }
        }
    }
})
