package com.actualadam.aoc2022.day13

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day13.Day13
import com.actualadam.aoc2022.day13.Day13.isInOrder
import com.actualadam.aoc2022.day13.Day13.part1
import com.actualadam.aoc2022.day13.Day13.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day13Test : FreeSpec({
    "day 12" - {
        val exampleInput = TestUtil.getInputLines("/day13/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day13/puzzle-input.txt")
        "part 1" - {
            "individual packets" - {
                "all ints same length" - {
                    val input = """
                        [1,1,3,1,1]
                        [1,1,5,1,1]
                    """.trimIndent()
//                    isInOrder(input) shouldBe true
                }
            }
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
