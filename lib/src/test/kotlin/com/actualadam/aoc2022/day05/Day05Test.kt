package com.actualadam.aoc2022.day05

import com.actualadam.aoc2022.TestUtil
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day05Test : FreeSpec({
    "Day05" - {
        val exampleInput = TestUtil.getInputLines("/day05/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day05/puzzle-input.txt")

        "Part 1" - {
            "Example" {
                Day05.part1(exampleInput) shouldBe "CMZ"
            }

            "Puzzle Input" {
                Day05.part1(puzzleInput) shouldBe "JCMHLVGMG"
            }
        }

        "Part 2" - {
            "Example" {
                Day05.part2(exampleInput) shouldBe "MCD"
            }

            "Puzzle Input" {
                Day05.part2(puzzleInput) shouldBe "MCD"
            }
        }

    }
})
