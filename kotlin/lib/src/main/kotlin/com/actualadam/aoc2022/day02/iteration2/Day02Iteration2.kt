package com.actualadam.aoc2022.day02.iteration2

object Day02Iteration2 {
    fun calculateScore(rounds: List<String>, decoder: (String) -> Int): Int =
        rounds.sumOf { decoder(it) }

    fun parseInput(input: String) = input.split("\n").map{
        it.replace("\\s".toRegex(), "")
    }


    fun part1(input: String): Int {
        fun decoder(input: String) =
            // symbol encodings
            // A|X means Rock
            // B|Y means Paper
            // C|Z means Scissors

            // Shape bonus
            // Rock:     1
            // Paper:    2
            // Scissors: 3
            when(input) {
                "AX" -> 4 // rock rock, draw for 3, +1 rock bonus
                "AY" -> 8 // rock paper, win for 6, +2 paper bonus
                "AZ" -> 3 // rock scissors, lose for 0, +3 scissors bonus
                "BX" -> 1 // paper rock lose for 0, +1 rock bonus
                "BY" -> 5 // paper paper draw for 3, +2 paper bonus
                "BZ" -> 9 // paper scissors win for 6, +3 scissors bonus
                "CX" -> 7 // scissors rock win for 6, +1 rock bonus
                "CY" -> 2 // scissors paper, lose for 0, +2 paper bonus
                "CZ" -> 6 // scissors scissors, draw for 3, +3 scissors bonus
                else -> throw IllegalArgumentException("didn't understand $input")
            }

        return calculateScore(parseInput(input), ::decoder)
    }

    fun part2(input: String): Int {
        /**
         * Opponent move symbols
         * A Rock
         * B Paper
         * C Scissors
         *
         * Shape bonuses
         * Rock     1
         * Paper    2
         * Scissors 3
         *
         * Your move symbols
         * X Lose
         * Y Draw
         * Z Win
         */
        fun decoder(input: String) =
            when(input) {
                "AX" -> 3 // they pick rock,     you lose 0 with scissors +3
                "AY" -> 4 // they pick rock,     you draw 3 with rock     +1
                "AZ" -> 8 // they pick rock,     you win  6 with paper    +2
                "BX" -> 1 // they pick paper,    you lose 0 with rock     +1
                "BY" -> 5 // they pick paper,    you draw 3 with paper    +2
                "BZ" -> 9 // they pick paper,    you win 6 with scissors  +3
                "CX" -> 2 // they pick scissors, you lose 0 with paper    +2
                "CY" -> 6 // they pick scissors, you draw 3 with scissors +3
                "CZ" -> 7 // they pick scissors, you win 6 with rock      +1
                else -> throw IllegalArgumentException("didn't understand $input")
            }
        return calculateScore(parseInput(input), ::decoder)
    }
}
