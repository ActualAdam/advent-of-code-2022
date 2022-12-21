package com.actualadam.aoc2022.day20

object Day20 {
    fun part1(lines: List<String>): Int {
        data class Number(
            val origIdx: Int,
            val value: Int,
        )
        val numberList = lines.mapIndexed { idx, value -> Number(idx, value.toInt()) }.toMutableList()
        numberList.indices.forEach { idx ->
            val idxToMove = numberList.indexOfFirst { it.origIdx == idx }
            val number = numberList.removeAt(idxToMove)
            val newIdx = (idxToMove + number.value).mod(numberList.size)
            numberList.add(newIdx, number)
        }
        val idxOf0 = numberList.indexOfFirst { it.value == 0 }
        return listOf(1000, 2000, 3000)
            .sumOf { numberList[(idxOf0 + it) % numberList.size].value }
    }

    fun part2(lines: List<String>): Long {
        data class Number(
            val origIdx: Int,
            val value: Long,
        )
        val decryptionKey = 811589153
        val numberList = lines.mapIndexed { idx, value -> Number(idx, value.toLong() * decryptionKey) }.toMutableList()
        repeat(10) {
            numberList.indices.forEach { idx ->
                val idxToMove = numberList.indexOfFirst { it.origIdx == idx }
                val number = numberList.removeAt(idxToMove)
                val newIdx = (idxToMove + number.value).mod(numberList.size)
                numberList.add(newIdx, number)
            }
        }
        val idxOf0 = numberList.indexOfFirst { it.value == 0L }
        return listOf(1000, 2000, 3000)
            .sumOf { numberList[(idxOf0 + it) % numberList.size].value }
    }
}
