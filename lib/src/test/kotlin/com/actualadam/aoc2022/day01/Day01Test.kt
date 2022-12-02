package com.actualadam.aoc2022.day01

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day01.Day01.getFattestStash
import com.actualadam.aoc2022.day01.Day01.getFattestStashes
import com.actualadam.aoc2022.day01.Day01.parse
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


class Day01Test : StringSpec({
    "part 1 example" {
       val stashes = parse(TestUtil.getInputString("/day01/part1-example.txt"))

       getFattestStash(stashes).single()
            .totalCalories
            .also { println("day 01 example: $it") }
          .shouldBe(24000)
   }

   "part 1 real input" {
      val stashes = parse(TestUtil.getInputString("/day01/part1-input.txt"))
         getFattestStash(stashes).single()
            .totalCalories
            .also { println("day 01 part 1 answer: $it") }
            .shouldBe( 71924)

   }

   "part 2 real input" {
      val stashes = parse(TestUtil.getInputString("/day01/part1-input.txt"))
         getFattestStashes(3, stashes)
            .sumOf { it.totalCalories }
            .also { println("day 01 part 2 answer: $it") }
            .shouldBe(210406)
   }
})
