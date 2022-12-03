package com.actualadam.aoc2022.day01

/**
 * splits the list into sublists based on the given predicate. Items in the list that satisfy the predicate
 * serve as delimiters for the resulting list of lists.
 */
fun <T> List<T>.splitOn(predicate: (T) -> Boolean): List<List<T>> {
    // determine the indices of this list that will be the slice points
    // the first index, the last index, and the indices before and after the index of any value that satisfies the predicate
    val slicePoints = flatMapIndexed { index: Int, item: T ->
        when {
            index == 0 || index == lastIndex -> listOf(index)
            predicate(item) -> listOf(index - 1, index + 1)// grouped funny, but we just flatten at the end anyway
            else -> emptyList()
        }
    }
    // now we have a flat list of index ranges for the sublists we need (from, to, from, to, from, to...)
    return slicePoints.chunked(2) { (from, to) ->
        slice(from..to)
    }
}

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
