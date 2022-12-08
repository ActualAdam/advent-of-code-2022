package com.actualadam.aoc2022.day07

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day07.Day07.part1
import com.actualadam.aoc2022.day07.Day07.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day07Test : FreeSpec({

    "day 7" - {
        val exampleInput = TestUtil.getInputLines("/day07/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day07/puzzle-input.txt")

        "part 1" - {
            "example" {
                part1(exampleInput) shouldBe 95437
            }
            "puzzle input" {
                part1(puzzleInput) shouldBe 1447046
            }
        }

        "part 2" - {
            "example" {
                part2(exampleInput) shouldBe 24933642
            }
            "puzzle input" {
                part2(puzzleInput) shouldBe 0
            }
        }
    }
})
