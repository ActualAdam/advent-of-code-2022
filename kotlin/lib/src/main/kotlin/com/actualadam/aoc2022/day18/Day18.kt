package com.actualadam.aoc2022.day18

import kotlin.math.abs


object Day18 {
    data class Grid3d(
        val adjacencyList: Map<Cube, List<Cube>>
    ) {
        companion object {
            fun from(lines: List<String>): Grid3d {
                val cubes = lines.map { line ->
                    line.split(",")
                        .map { it.toInt() }
                }.map { Cube(it[0], it[1], it[2]) }

                return Grid3d(
                    cubes.associateWith { cube ->
                        cubes.filter { it.isAdjacentTo(cube) }
                    }
                )
            }
        }
    }

    data class Cube(
        val x: Int,
        val y: Int,
        val z: Int,
    ) {
        fun manhattanDistance(other: Cube): Int =
            abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

        fun isAdjacentTo(other: Cube) =
            manhattanDistance(other) == 1
    }

    fun part1(lines: List<String>): Int {
        val grid = Grid3d.from(lines)
        val potentialSides =
            grid.adjacencyList.keys.size * 6 // each cube would have 6 sides if they were completely disconnected
        val connectedSides = grid.adjacencyList.values.flatten().size
        return potentialSides - connectedSides
    }

    fun part2(lines: List<String>): Int {

        return 0
    }
}
