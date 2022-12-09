package com.actualadam.aoc2022.day02.iteration3

import com.actualadam.aoc2022.component1
import com.actualadam.aoc2022.component2
import com.actualadam.aoc2022.component3
import com.actualadam.aoc2022.day02.iteration3.Day02Iteration3.Outcome.*
import com.actualadam.aoc2022.day02.iteration3.Day02Iteration3.Shape.*

object Day02Iteration3 {
    enum class Shape(val bonusScore: Int) {
        Rock(1),
        Paper(2),
        Scissors(3),
        ;
        fun winAgainst(): Shape =
            when (this) {
                Rock -> Paper
                Paper -> Scissors
                Scissors -> Rock
            }
    }

    enum class Outcome(val score: Int) {
        Lose(0),
        Draw(3),
        Win(6),
        ;
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
                Win -> theirs.winAgainst()
                Lose -> loseAgainst(theirs)
                Draw -> theirs
            }
            outcome.score + yours.bonusScore
        }
    }
}
