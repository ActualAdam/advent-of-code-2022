package com.actualadam.aoc2022.day19

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day19.Day19.part1
import com.actualadam.aoc2022.day19.Day19.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day19Test : FreeSpec({
    "Day 19" - {
        val exampleInput = TestUtil.getInputLines("/day19/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day19/puzzle-input.txt")
        "part 1" - {
            "example" - {
                part1(exampleInput) shouldBe 33
            }
            "puzzle" - {
                part1(puzzleInput) shouldBe 0
            }
        }
        "part 2" - {
            "example" - {
                part2(exampleInput) shouldBe 0
            }
            "puzzle" - {
                part2(puzzleInput) shouldBe 0
            }
        }

    }
})
