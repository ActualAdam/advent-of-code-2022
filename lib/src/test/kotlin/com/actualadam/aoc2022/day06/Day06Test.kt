package com.actualadam.aoc2022.day06

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day06.Day06.part1
import com.actualadam.aoc2022.day06.Day06.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day06Test : FreeSpec({
    "Day 06" - {
        "Part 1" - {
            "Examples" {
                part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb") shouldBe 7
                part1("bvwbjplbgvbhsrlpgdmjqwftvncz") shouldBe 5
                part1("nppdvjthqldpwncqszvftbrmjlhg") shouldBe 6
                part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") shouldBe 10
                part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") shouldBe 11
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
