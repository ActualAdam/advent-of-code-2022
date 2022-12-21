package com.actualadam.aoc2022.day13

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day13.Day13
import com.actualadam.aoc2022.day13.Day13.isInOrder
import com.actualadam.aoc2022.day13.Day13.parse
import com.actualadam.aoc2022.day13.Day13.part1
import com.actualadam.aoc2022.day13.Day13.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day13Test : FreeSpec({
    "day 12" - {
        val exampleInput = TestUtil.getInputLines("/day13/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day13/puzzle-input.txt")
        "part 1" - {
            "example" {
                part1(exampleInput) shouldBe 13
            }
            "puzzle" {
                part1(puzzleInput) shouldBe 5675
            }

        }

        "part 2" - {
            "example" {
                part2(exampleInput) shouldBe 140
            }
            "puzzle" {
                part2(puzzleInput) shouldBe 20383
            }
        }
    }
})
