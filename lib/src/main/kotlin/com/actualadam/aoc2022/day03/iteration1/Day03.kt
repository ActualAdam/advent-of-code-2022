package com.actualadam.aoc2022.day03.iteration1

/**
 * encode characters
 * a-z : 1-26
 * A-Z : 27-52
 */
fun charCode(char: Char): Int {
    return if (char.isLowerCase()) { char.code - 96 }
    else if (char.isUpperCase()) { char.code - 38 }
    else throw IllegalArgumentException ("not a letter")
}

object Day03 {
    fun part1(lines: List<String>): Int {
        fun bisectLine(line: String): List<Set<Char>> =
            line.chunked(line.length / 2)
                .map { it.toSet() }

        return lines.sumOf {
            val duplicateItem = bisectLine(it).reduce(Set<Char>::intersect).single()
            charCode(duplicateItem)
        }
    }


    fun part2(inputLines: List<String>): Any {
        val groups = inputLines.map{it.toSet()}.chunked(3)
        return groups.sumOf {
            val badge = it.reduce(Set<Char>::intersect).single()
            charCode(badge)
        }
    }
}
