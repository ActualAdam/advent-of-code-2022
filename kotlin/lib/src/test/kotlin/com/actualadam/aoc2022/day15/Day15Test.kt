package com.actualadam.aoc2022.day15

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day15.Day15.part1
import com.actualadam.aoc2022.day15.Day15.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day15Test : FreeSpec({
    "day 15" - {
        val exampleInput = TestUtil.getInputLines("/day15/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day15/puzzle-input.txt")
        "part 1" - {
            "example" {
                part1(exampleInput, 10) shouldBe 26
            }
            "puzzle" {
                part1(puzzleInput, 2_000_000) shouldBe 5176944
            }
        }
        "part 2" - {
            "example" {
                part2(exampleInput, 20) shouldBe 56000011
            }
            "puzzle" {
                part2(puzzleInput, 4_000_000) shouldBe 13350458933732L
            }
        }
    }
})
