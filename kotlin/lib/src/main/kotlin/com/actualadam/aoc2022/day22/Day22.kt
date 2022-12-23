package com.actualadam.aoc2022.day22

object Day22 {
    data class Board(
        val tiles: List<Tile>
    ) {
        companion object {

        }
    }
    data class Position(
        val x: Int = 0,
        val y: Int = 0,
    )
    data class Tile(
        val position: Position,
        val type: TileType,
    )
    enum class TileType(val display: Char) {
        Open('.'),
        Wall('#'),
        ;
    }
    fun part1(lines: String): Int {
       return 0
    }

    fun part2(lines: String): Int {
        return 0
    }
}
