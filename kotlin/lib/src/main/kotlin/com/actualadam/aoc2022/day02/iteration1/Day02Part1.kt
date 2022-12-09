package com.actualadam.aoc2022.day02.iteration1

import com.actualadam.aoc2022.day02.iteration1.Day02Part1.Shape.*

object Day02Part1 {
    data class Guide(
        val rounds: List<Round>,
    ){
        val yourTotalScore = rounds.sumOf { it.yourScore() }

        companion object {
            fun parse(input: String): Guide {
                return Guide(rounds = input.trim().split('\n').map { Round.parse(it) })
            }
        }
    }

    data class Round(
        val theirs: Shape,
        val yours: Shape,
    ) {
        private val shapeThatBeats = mapOf(
            Rock to Paper,
            Paper to Scissors,
            Scissors to Rock,
        )

        fun yourScore(): Int =
            when (yours) {
                shapeThatBeats[theirs] -> 6
                theirs -> 3
                else -> 0
            } + yours.bonusScore

        companion object {
            fun parse(line: String): Round {
                val (theirs, yours) = line.trim().split(" ").map { it.single() }
                return Round(Shape.parse(theirs), Shape.parse(yours))
            }
        }
    }
    enum class Shape(
        val theirSymbol: Char,
        val yourSymbol: Char,
        val bonusScore: Int,
    ) {
        Rock(theirSymbol = 'A', yourSymbol = 'X', bonusScore = 1),
        Paper('B', 'Y', 2),
        Scissors('C', 'Z', 3),
        ;

        companion object {
            val shapesBySymbol = values().associateBy { it.theirSymbol } + values().associateBy { it.yourSymbol }
            fun parse(symbol: Char): Shape = shapesBySymbol[symbol]!!
        }
    }

    fun part01(input: String): Int {
        val guide = Guide.parse(input)
        return guide.yourTotalScore
    }
}
