package com.actualadam.aoc2022.day01

import com.actualadam.aoc2022.splitOn

object Day01Iteration2 {
    /**
     * split the lines into sublists using the empty lines as delimiters
     * each sublist represents a single elf's stash of snacks
     * convert the remaining values in each sublist to integers so they can be summed.
     * the result is the total calories in each elf's stash
     */
    fun sumGroups(lines: List<String>) =
        lines.splitOn { it.isEmpty() }.map { stash ->
        stash.sumOf { snackCalories ->
            snackCalories.toInt()
        }
    }

    // get the top stash in terms of calories
    fun part1(lines: List<String>): Int = sumGroups(lines).max()

    // get the top 3 and sum their calories
    fun part2(lines: List<String>): Int = sumGroups(lines).sortedDescending().take(3).sum()
}
