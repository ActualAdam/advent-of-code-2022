package com.actualadam.aoc2022.day17

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day17.Day17.part1
import com.actualadam.aoc2022.day17.Day17.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day17Test : FreeSpec({
    val exampleInput = TestUtil.getInputString("/day17/example.txt")
    val puzzleInput = TestUtil.getInputString("/day17/puzzle-input.txt")
    "Day 17" - {
        "Part 1" -{
            "example" {
               part1(exampleInput) shouldBe 3068
            }
            "puzzle" {
                part1(puzzleInput) shouldBe 0
            }
        }
        "Part 2" -{
            "example" {
                part2(exampleInput) shouldBe 0
            }
            "puzzle" {
                part2(puzzleInput) shouldBe 0
            }
        }
    }
}) {
}
