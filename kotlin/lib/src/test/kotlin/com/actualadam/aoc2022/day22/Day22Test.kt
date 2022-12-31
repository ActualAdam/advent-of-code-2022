package com.actualadam.aoc2022.day22

import io.kotest.core.spec.style.FreeSpec
import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day22.Day22.part1
import com.actualadam.aoc2022.day22.Day22.part2

import io.kotest.matchers.shouldBe

class Day22Test() : FreeSpec({
        "Day 22" - {
                val exampleInput = TestUtil.getInputLines("/day22/example.txt")
                val puzzleInput = TestUtil.getInputLines("/day22/puzzle-input.txt")
                "Part 1" - {
                        "Example" {
                                part1(exampleInput) shouldBe 6032
                        }
                        "Puzzle" {
                                part1(puzzleInput) shouldBe 0
                        }
                }
                "Part 2" - {
                        "Example" {
                                part2(exampleInput) shouldBe 0
                        }
                        "Puzzle" {
                                part2(puzzleInput) shouldBe 0
                        }
                }
        }
})
