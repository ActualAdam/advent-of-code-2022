package com.actualadam.aoc2022.day21

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day21.Day21.part1
import com.actualadam.aoc2022.day21.Day21.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day21Test : FreeSpec({
    "Day 21" - {
        val exampleInput = TestUtil.getInputLines("/day21/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day21/puzzle-input.txt")
        "part 1" - {
            "example" {
               part1(exampleInput) shouldBe 152
            }
            "puzzle" {
                part1(puzzleInput) shouldBe 51928383302238
            }
        }
        "part 2" - {
            "example" {
                part2(exampleInput) shouldBe 301
            }
            "puzzle" {
                part2(puzzleInput) shouldBe 0
            }
        }
    }
})
