package com.actualadam.aoc2022

import com.actualadam.aoc2022.day08.Day08.part1
import com.actualadam.aoc2022.day08.Day08.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day08Test : FreeSpec({
    "day 08" - {
        val exampleInput = TestUtil.getInputLines("/day08/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day08/puzzle-input.txt")
        "part 1" - {
            "example" - {
                part1(exampleInput) shouldBe 21
            }
            "puzzle input" - {
                part1(puzzleInput) shouldBe 1679
            }
        }

        "part 2" - {
            "example" - {
                part2(exampleInput) shouldBe 8
            }
            "puzzle input" - {
                part2(puzzleInput) shouldBe 536625
            }
        }
    }
}) {
}
