package com.actualadam.aoc2022.day04

import io.kotest.matchers.uri.haveFragment

object Day04 {

    infix fun IntRange.fullyContains(other: IntRange): Boolean =
        contains(other.first) && contains(other.last)

    fun IntRange.countIntersections(other: IntRange): Int {
        val (lower, upper) = listOf(this, other).sortedBy { it.first }
        return if (upper.first <= lower.last) {
            lower.last - upper.first + 1 // range is inclusive
        } else {
            0
        }
    }

    infix fun IntRange.overlaps(other: IntRange): Boolean {
        val (lower, upper) = listOf(this, other).sortedBy { it.first }
        return upper.first <= lower.last
    }

    fun part1(lines: List<String>): Int {
        return lines.map { line ->
            parsePairIntRanges(line)
        }.count {
            it.first fullyContains it.second || it.second fullyContains it.first
        }
    }

    private fun parsePairIntRanges(line: String): Pair<IntRange, IntRange> {
        val ranges = line.split(",")
            .map { range ->
                val (first, last) = range.split("-").map { it.toInt() }
                first..last
            }
        return Pair(ranges[0], ranges[1])
    }

    fun part2(lines: List<String>): Int =
        lines.map(::parsePairIntRanges).count { range ->
            range.first overlaps range.second
        }
}
