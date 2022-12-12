package com.actualadam.aoc2022.day12

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day12.Day12.part1
import com.actualadam.aoc2022.day12.Day12.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day12Test : FreeSpec({
    "day 12" - {
        val exampleInput = TestUtil.getInputLines("/day12/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day12/puzzle-input.txt")
        "part 1" - {
            "example" {
                part1(exampleInput) shouldBe 31
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
