package com.actualadam.aoc2022.day01

object Day01Iteration1 {
    data class Snack(
        val calories: Int
    ) {
        companion object {
            fun parse(input: String) = Snack(calories = input.toInt())
        }
    }

    data class Stash(
        val snacks: List<Snack>
    ) {
        val totalCalories = snacks.sumOf { it.calories }

        companion object {
            fun parse(input: String) =
                Stash(snacks = input
                    .split("\n")
                    .map { Snack.parse(it) })

        }
    }

    fun parse(input:String): List<Stash> = input
        .split("\n\n")
        .map { Stash.parse(it) }

    fun getFattestStashes(count: Int, stashes:List<Stash>): List<Stash> =
        stashes
            .sortedByDescending { it.totalCalories }
            .take(count)

    fun getFattestStash(stashes:List<Stash>) = getFattestStashes(1, stashes)
}
