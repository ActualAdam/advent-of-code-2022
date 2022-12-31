package com.actualadam.aoc2022.day14

object Day14 {
    data class Position(
        val x: Int,
        val y: Int,
    ) {
        companion object {
            fun from(string: String): Position {
                val (x,y) = string.split(",").map { it.toInt() }
                return Position(x,y)
            }
        }
    }

    fun createPath(line: String): List<Pair<Position, Position>> {
       val coords = line.split(" -> ")
        val positions = coords.map { Position.from(it) }
        val pairs = positions.zipWithNext()
        return pairs
    }

    fun part1(lines: List<String>): Int {

        return 0
    }
    fun part2(lines: List<String>): Int {
        return 0
    }

}
