package com.actualadam.aoc2022.day06

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day06.Day06.part1
import com.actualadam.aoc2022.day06.Day06.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Day06Test : FreeSpec({
    "Day 06" - {
        "Part 1" - {
            "Examples" - {
                data class Day06Data(val input: String, val expected: Int)
                withData(
                    Day06Data("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 7),
                    Day06Data("bvwbjplbgvbhsrlpgdmjqwftvncz", 5),
                    Day06Data("nppdvjthqldpwncqszvftbrmjlhg", 6),
                    Day06Data("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10),
                    Day06Data("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11),
                ) { (input, expected) ->
                    part1(input) shouldBe expected
                }
            }
            "Puzzle Input" {
                val input = TestUtil.getInputString("/day06/puzzle-input.txt").trim()
                part1(input) shouldBe 1920
            }
        }
        "Part 2" - {
            "Puzzle Input" {
                val input = TestUtil.getInputString("/day06/puzzle-input.txt").trim()
                part2(input) shouldBe 2334
            }
        }
    }
})
