package com.actualadam.aoc2022.day20

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day20.Day20.part1
import com.actualadam.aoc2022.day20.Day20.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day20Test : FreeSpec({
    "Day 20" - {
        val exampleInput = TestUtil.getInputLines("/day20/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day20/puzzle-input.txt")
        "part 1" - {
            "example" - {
                part1(exampleInput) shouldBe 3
            }
            "puzzle" - {
                part1(puzzleInput) shouldBe 7713
            }
        }
        "part 2" - {
            "example" - {
                part2(exampleInput) shouldBe 1623178306
            }
            "puzzle" - {
                part2(puzzleInput) shouldBe 1664569352803L
            }
        }

    }
})
