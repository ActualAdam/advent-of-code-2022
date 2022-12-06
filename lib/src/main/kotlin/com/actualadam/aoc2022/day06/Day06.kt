package com.actualadam.aoc2022.day06

object Day06 {
    fun part1(input: String): Int =
        input.windowed(4, 1)
            .mapIndexed { index, it -> Pair(index, it.toSet()) }
            .first { it.second.size == 4 }
            .first + 4

    fun part2(input: String): Int =
        input.windowed(14, 1)
            .mapIndexed { index, it -> Pair(index, it.toSet()) }
            .first { it.second.size == 14 }
            .first + 14
}
