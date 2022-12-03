package com.actualadam.aoc2022.day02.iteration1

import com.actualadam.aoc2022.day02.iteration1.Day02Part2.Shape.*

object Day02Part2 {
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

        fun yourScore(): Int =
            when (yours) {
                shapeThatBeats[theirs] -> 6
                theirs -> 3
                else -> 0
            } + yours.bonusScore

        companion object {
            private val shapeThatBeats = mapOf(
                Rock to Paper,
                Paper to Scissors,
                Scissors to Rock,
            )
            fun parse(line: String): Round {
                val (theirSymbol, responseSymbol) = line.trim().split(" ").map { it.single() }
                val theirs = Shape.parse(theirSymbol)
                val shapeThatBeatsTheirs: Shape = shapeThatBeats[theirs]!!
                val yours = when (responseSymbol) {
                    'Z' -> shapeThatBeatsTheirs
                    'Y' -> theirs
                    else -> (Shape.values().toSet() - setOf(theirs, shapeThatBeatsTheirs)).single()
                }
                return Round(theirs, yours)
            }
        }
    }

    enum class Shape(
        val symbol: Char,
        val bonusScore: Int,
    ) {
        Rock(symbol = 'A', bonusScore = 1),
        Paper('B', 2),
        Scissors('C', 3),
        ;

        companion object {
            private val shapesBySymbol = values().associateBy { it.symbol }
            fun parse(symbol: Char): Shape = shapesBySymbol[symbol]!!
        }
    }

    fun part02(input: String): Int {
        val guide = Guide.parse(input)
        return guide.yourTotalScore
    }
}
