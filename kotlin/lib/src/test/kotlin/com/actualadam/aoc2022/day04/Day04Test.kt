package com.actualadam.aoc2022.day04

import com.actualadam.aoc2022.TestUtil
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day04Test : FreeSpec({
    val exampleInput = TestUtil.getInputLines("/day04/example.txt")
    val puzzleInput = TestUtil.getInputLines("/day04/puzzle-input.txt")

    "Part 1 - full overlaps" - {
        "Example" {
            Day04.part1(exampleInput) shouldBe 2
        }
        "Puzzle" {
            Day04.part1(puzzleInput) shouldBe 462
        }
    }

    "Part 2" - {
        "Example" {
            Day04.part2(exampleInput) shouldBe 4
        }

        "Puzzle" {
            Day04.part2(puzzleInput) shouldBe 835
        }
    }
})
