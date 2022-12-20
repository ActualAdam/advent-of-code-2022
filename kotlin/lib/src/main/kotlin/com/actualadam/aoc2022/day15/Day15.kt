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

    fun part2(lines: List<String>, gridMax: Int): Long {
        // If I'm readying right, the problem can be summarized as, which node in the grid is not covered by a sensor.
        // Assume a 4Mx4M grid.
        //
        // This means if I can, using manhattan distances, get the radius of each sensor, union the coverage areas and subtract the set of nodes from the total grid nodes, the result should be a single node.
        // defining the radius as the manhattan distance between the sensor and the closest beacon.
        val deployments = lines.map { SensorDeployment.from(it) }
        var rowWithGap: Pair<Int, Collection<IntRange>>? = null
        for (row in 0 .. gridMax) {
            val blackoutRanges = deployments.map { it.blackoutRangeOnRow(row) }
            val truncated = blackoutRanges.map { it.coerceIn(0, gridMax) }
            val merged = mergeIntRanges(truncated)
            if(merged.size > 1) {
                rowWithGap = Pair(row, merged)
                break
            }
        }

        val x = rowWithGap!!.second.first().last + 1
        val y = rowWithGap.first


        return x.toLong() * 4_000_000    + y
    }

    fun mergeIntRanges(ranges: Collection<IntRange>): Collection<IntRange> {
        val sorted = ranges.sortedWith(intRangeComparator).toMutableList()
        val results = mutableListOf(sorted.removeFirst())
        while(sorted.any())  {
            val cur = sorted.removeFirst()
            val lastResult = results.last()
            if (cur == lastResult) continue
            if (cur.first <= lastResult.last) {
                if (lastResult.last >= cur.last) continue
                val merged = IntRange(lastResult.first, cur.last)
                results.removeLast()
                results.add(merged)
            } else {
                results.add(cur)
            }
        }
        return results
    }

    val intRangeComparator = Comparator<IntRange> { a, b ->
        if (a.first == b.first) {
            a.last.compareTo(b.last)
        } else {
            a.first.compareTo(b.first)
        }
    }

    fun IntRange.coerceIn(min: Int, max: Int) = IntRange(this.first.coerceIn(min, max), this.last.coerceIn(min, max))
}
