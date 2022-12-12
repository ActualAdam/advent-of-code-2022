package com.actualadam.aoc2022.day11

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day11.Day11.part1
import com.actualadam.aoc2022.day11.Day11.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day11Test : FreeSpec({
    "Day 11" - {
        val exampleInput = TestUtil.getInputLines("/day11/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day11/puzzle-input.txt")
        "Part 1" - {
            "Example" {
                part1(exampleInput) shouldBe 10605
            }
            "Puzzle" {
                part1(puzzleInput) shouldBe 316888
            }
        }
        "Part 2" - {
            "Example" {
                part2(exampleInput) shouldBe 2713310158
//                part2(exampleInput) shouldBe 99 * 103
            }
            "Puzzle" {
                part2(exampleInput) shouldBe 0
            }
        }
    }
})
