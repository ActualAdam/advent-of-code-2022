package com.actualadam.aoc2022.day15

import com.actualadam.aoc2022.day15.Day15.Direction.*
import kotlin.math.abs


object Day15 {
    data class Position(
        val x: Int,
        val y: Int,
    ) {
        fun manhattanDistanceFrom(position2: Position): Int =
            abs(x - position2.x) + abs(y - position2.y)

        fun move(direction: Direction, distance: Int) =
            when (direction) {
                LEFT -> copy(x = x - distance)
                UP -> copy(y = y - distance)
                DOWN -> copy(y = y + distance)
                RIGHT -> copy( x = x + distance)
            }
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT,
        ;
    }

    data class SensorDeployment(
        val sensorPosition: Position,
        val closestBeaconPosition: Position,
    ) {
        fun blackoutRangeOnRow(row: Int): IntRange {
           val distanceToBeacon = sensorPosition.manhattanDistanceFrom(closestBeaconPosition)
            val distanceToTargetRow = abs(row - sensorPosition.y)
            if (distanceToTargetRow > distanceToBeacon) {
                return IntRange(0, 0)
            }
            val oneSide = distanceToBeacon - distanceToTargetRow
            return IntRange(sensorPosition.x - oneSide, sensorPosition.x + oneSide)
        }

        companion object {
            fun from(line: String): SensorDeployment {
                val (sensorX, sensorY, beaconX, beaconY) = line
                    .split(" ")
                    .filter { it.contains("=")}
                    .map { assignment ->
                        assignment.dropWhile { !it.isDigit() && it != '-' }
                            .dropLastWhile{ it == ',' || it == ':' }
                            .toInt()
                    }
                return SensorDeployment(Position(sensorX, sensorY), Position(beaconX, beaconY))
            }
        }
    }


    fun knownBeaconsOnRow(deployments: List<SensorDeployment>, row: Int) =
        deployments.mapNotNull { if(it.closestBeaconPosition.y == row) it.closestBeaconPosition else null }


    fun part1(lines: List<String>, row: Int): Int {
        val deployments = lines.map { SensorDeployment.from(it) }
        val blackoutRanges = deployments.map { it.blackoutRangeOnRow(row) }
        val grossBlackouts = blackoutRanges.fold(setOf<Int>()) { a, b -> a.union(b) }.map { Position(it, row) }
        val netBlackouts = grossBlackouts - knownBeaconsOnRow(deployments, row).toSet()
        return netBlackouts.count()
    }

    fun part2(lines: List<String>): Int {
        return 0
    }
}
