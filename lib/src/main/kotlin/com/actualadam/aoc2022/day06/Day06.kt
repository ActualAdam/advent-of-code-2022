package com.actualadam.aoc2022.day06

object Day06 {
    fun part1(input: String): Int = detectMarker(input, 4)

    fun part2(input: String): Int = detectMarker(input, 14)

    fun detectMarker (input: String, sequenceLength: Int): Int =
        input.windowed(sequenceLength, 1)
            .mapIndexed { index, it -> Pair(index, it.toSet()) }
            .first { it.second.size == sequenceLength }
            .first + sequenceLength
}
