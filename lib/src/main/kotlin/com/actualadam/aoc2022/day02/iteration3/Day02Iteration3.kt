package com.actualadam.aoc2022.day02.iteration3

import com.actualadam.aoc2022.day02.iteration3.Day02Iteration3.Outcome.*
import com.actualadam.aoc2022.day02.iteration3.Day02Iteration3.Shape.*

object Day02Iteration3 {
    enum class Shape(val bonusScore: Int) {
        Rock(1),
        Paper(2),
        Scissors(3),
        ;
    }

    enum class Outcome(val score: Int) {
        Lose(0),
        Draw(3),
        Win(6),
        ;
    }

    fun winAgainst(shape: Shape): Shape =
        when (shape) {
            Rock -> Paper
            Paper -> Scissors
            Scissors -> Rock
        }

    fun loseAgainst(shape: Shape): Shape =
        when (shape) {
            Rock -> Scissors
            Paper -> Rock
            Scissors -> Paper
        }


    fun calculateOutcome(theirs: Shape, yours: Shape): Outcome =
        when (Pair(theirs, yours)) {
            Pair(Rock, Paper) -> Win
            Pair(Rock, Scissors) -> Lose
            Pair(Paper, Rock) -> Lose
            Pair(Paper, Scissors) -> Win
            Pair(Scissors, Rock) -> Win
            Pair(Scissors, Paper) -> Lose
            else -> Draw
        }

    fun parseTheirs(theirs: Char): Shape =
        when (theirs) {
            'A' -> Rock
            'B' -> Paper
            'C' -> Scissors
            else -> throw IllegalArgumentException("unknown input: $theirs")
        }


    // allows destructuring like `val (yours, _, theirs) = "A Y"`
    private operator fun String.component1() = this[0]
    private operator fun String.component2() = this[1]
    private operator fun String.component3() = this[2]

    fun part1(lines: List<String>): Int {
        fun parseYours(yours: Char): Shape =
            when (yours) {
                'X' -> Rock
                'Y' -> Paper
                'Z' -> Scissors
                else -> throw IllegalArgumentException("unknown input: $yours")
            }

        return lines.sumOf {
            val (theirCode, _, yourCode) = it
            val theirs = parseTheirs(theirCode)
            val yours = parseYours(yourCode)
            calculateOutcome(theirs, yours).score + yours.bonusScore
        }
    }

    fun part2(lines: List<String>): Int {
        fun parseOutcome(outcomeCode: Char): Outcome =
            when (outcomeCode) {
                'X' -> Lose
                'Y' -> Draw
                'Z' -> Win
                else -> throw IllegalArgumentException("unknown input: $outcomeCode")
            }

        return lines.sumOf {
            val (theirCode, _, outcomeCode) = it
            val theirs = parseTheirs(theirCode)
            val outcome = parseOutcome(outcomeCode)
            val yours = when (outcome) {
                Win -> winAgainst(theirs)
                Lose -> loseAgainst(theirs)
                Draw -> theirs
            }
            outcome.score + yours.bonusScore
        }
    }
}
