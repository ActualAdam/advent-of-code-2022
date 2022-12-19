package com.actualadam.aoc2022.day18

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day18.Day18
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day18Test : FreeSpec({
    "day 18" - {
        val exampleInput = TestUtil.getInputLines("/day18/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day18/puzzle-input.txt")
        "part 1" - {
            "briefExample" {
               val input = """
                    1,1,1
                    2,1,1
                """.trimMargin().split("\n").map{it.trim()}
                Day18.part1(input) shouldBe 10
            }
            "example" {
                Day18.part1(exampleInput) shouldBe 64
            }
            "puzzle" {
                Day18.part1(puzzleInput) shouldBe 3500
            }
        }
        "part 2" - {
            "example" {
                Day18.part2(exampleInput) shouldBe 58
            }
            "puzzle" {
                Day18.part2(puzzleInput) shouldBe 0
            }
        }
    }    
})
