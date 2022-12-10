package com.actualadam.aoc2022.day10

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day10.Day10.part1
import com.actualadam.aoc2022.day10.Day10.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day10Test : FreeSpec({
    "Day 10" - {
        val exampleInput = TestUtil.getInputLines("/day10/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day10/puzzle-input.txt")
        "part 1" - {
            "example" {
                part1(exampleInput) shouldBe 13140
            }

            "puzzle" {
                part1(puzzleInput) shouldBe 0
            }
        }
        "part 2" - {
            "example" {
                part2(exampleInput) shouldBe 0
            }

            "puzzle" {
                part2(puzzleInput) shouldBe 0
            }
        }
    }
})
