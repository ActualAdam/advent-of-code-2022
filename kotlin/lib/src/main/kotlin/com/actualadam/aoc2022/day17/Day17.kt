package com.actualadam.aoc2022.day17

object Day17 {
    enum class JetDirection(serialized: Char) {
        Left('<'),
        Right('>'),
    }

    data class Chamber(
        val jetPattern: List<JetDirection>,
        var ticksElapsed: Int,
        var towerHigher: Int,
        // when ticksElapsed is odd, it's the jetPattern's move
        // when ticksElapsed is even, it's downward move
        // next jetPattern direction is ticksElapsed % jetPattern.size
        // there's a chamber grid, the value of the grid is the state: empty, fallingRock, restingRock.
        // each grid row 7 units wide
        // new rocs appear 3 unites up from the floor or the top of the tower (restingRocks)
        // this means we need to add height to the chamber before each rock appears if the chamber height is < 3 taller than the tower.
        // when a jet blows a rock and it's already touching the chamber side boundary, nothing happens
        
    )
    fun part1(lines: String): Int{
        return 0
    }
    fun part2(lines: String): Int{
        return 0
    }
}
