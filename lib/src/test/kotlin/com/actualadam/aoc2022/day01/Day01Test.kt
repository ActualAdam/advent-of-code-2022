package com.actualadam.aoc2022.day01

import com.actualadam.aoc2022.TestUtil
import com.actualadam.aoc2022.day01.Day01.getFattestStash
import com.actualadam.aoc2022.day01.Day01.getFattestStashes
import com.actualadam.aoc2022.day01.Day01.parse
import kotlin.test.Test
import kotlin.test.assertEquals


class Day01Test {
   @Test fun `part 1 example`() {
      val stashes = parse(TestUtil.getInputString("/day01/part1-example.txt"))
      assertEquals(
         24000,
         getFattestStash(stashes).single()
            .totalCalories
            .also { println("day 01 example: $it") })
   }

   @Test fun `part 1 real input`() {
      val stashes = parse(TestUtil.getInputString("/day01/part1-input.txt"))
      assertEquals(
         71924,
         getFattestStash(stashes).single()
            .totalCalories
            .also { println("day 01 part 1 answer: $it") })

   }

   @Test fun `part 2 real input`() {
      val stashes = parse(TestUtil.getInputString("/day01/part1-input.txt"))
      assertEquals(
         210406,
         getFattestStashes(3, stashes)
            .sumOf { it.totalCalories }
            .also { println("day 01 part 2 answer: $it") })
   }




}
