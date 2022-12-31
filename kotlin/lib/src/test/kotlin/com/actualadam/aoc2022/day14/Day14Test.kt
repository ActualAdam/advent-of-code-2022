package com.actualadam.aoc2022.day14

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day14.Day14.Position
import com.actualadam.aoc2022.day14.Day14.createPath
import com.actualadam.aoc2022.day14.Day14.part1
import com.actualadam.aoc2022.day14.Day14.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe

class Day14Test : FreeSpec({
    "Day 14" - {
        val exampleInput = TestUtil.getInputLines("/day14/example.txt")
        val puzzleInput = TestUtil.getInputLines("/day14/puzzle-input.txt")
        "Part 1" - {
            "units" - {
                "coordinates like `498,4` can be transformed to a Position object" {
                    Position.from("498,4") shouldBe Position(498, 4)
                }
                "A path like `498,4 -> 498,6 -> 496,6` can be transformed to Pairs of Positions" {
                    val path = "498,4 -> 498,6 -> 496,6"
                    createPath(path) shouldContainInOrder listOf(
                        Position(498, 4) to Position(498, 6),
                        Position(498,6) to Position(496,6),
                    )
                }
            }
            "example" {
                part1(exampleInput) shouldBe 0
            }
            "puzzle input" {
                part1(puzzleInput) shouldBe 0
            }
        }
        "Part 2" - {
            "example" {
                part2(exampleInput) shouldBe 0
            }
            "puzzle input" {
                part2(puzzleInput) shouldBe 0
            }
        }
    }
})
