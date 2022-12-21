package com.actualadam.aoc2022.day13

import kotlinx.serialization.json.*

object Day13 {
    data class TreeNode<T : Comparable<T>>(
        val value: T,
        val children: MutableList<TreeNode<T>>? = null,
    ) {
        companion object {
            fun from(string: String): JsonArray = Json.parseToJsonElement(string).jsonArray
        }
    }

    private enum class TypeCase {
        BothIntegers,
        BothLists,
        OneIntegerOneList,
    }

    private fun getTypeCase(pair: Pair<JsonElement, JsonElement>): TypeCase =
        pair.toList().map { it::class.simpleName }.toSet().let {
            if (it.size == 2) {
                TypeCase.OneIntegerOneList
            } else {
                when (pair.first) {
                    is JsonArray -> TypeCase.BothLists
                    is JsonPrimitive -> TypeCase.BothIntegers
                    else -> throw IllegalStateException("didn't plan for ${pair.first::class.simpleName}")
                }
            }
        }

    fun parse(string: String): JsonElement = Json.parseToJsonElement(string).jsonArray

    val packetComparator = Comparator { left: JsonElement, right: JsonElement ->
        when(isInOrder(Pair(left, right))) {
            true -> -1
            false -> 1
            null -> 0
        }
    }

    fun isInOrder(packetData: Pair<JsonElement, JsonElement>): Boolean? {
        when (getTypeCase(packetData)) {
            TypeCase.BothIntegers -> {
                val left = packetData.first.jsonPrimitive.content.toInt()
                val right = packetData.second.jsonPrimitive.content.toInt()
                if (left < right) return true
                if (left > right) return false
                return null
            }
            TypeCase.BothLists -> {
                val left = packetData.first.jsonArray
                val right = packetData.second.jsonArray
                left.zip(right).forEach {
                    val res = isInOrder(it)
                    if (res!= null) return res
                }
                if (left.size < right.size) return true
                if (left.size > right.size) return false
                return null
            }
            TypeCase.OneIntegerOneList -> {
                val left = if (packetData.first is JsonPrimitive) JsonArray(content = listOf(packetData.first)) else packetData.first
                val right = if (packetData.second is JsonPrimitive) JsonArray(content = listOf(packetData.second)) else packetData.second
                val res = isInOrder(Pair(left, right))
                if (res!= null) return res
            }
        }
        return null
    }

    fun part1(lines: List<String>) : Int {


        val packetPairs = lines.chunked(3).map { Pair(parse(it[0]), parse(it[1])) }


        val validated = packetPairs.mapIndexed { idx, packetPair -> Pair(idx, isInOrder(packetPair)) }
        return validated
            .filter { it.second!! }
            .sumOf{
                it.first + 1
            }
    }
    fun part2(lines: List<String>) : Int {
        val firstDividerPacket = "[[2]]"
        val secondDividerPacket = "[[6]]"
        val packets = lines
            .filterNot { it.isEmpty() }
            .plus(listOf(firstDividerPacket, secondDividerPacket))
            .map { parse(it) }
        val sorted = packets.sortedWith(packetComparator).mapIndexed { idx, packet -> Pair(idx, packet.toString()) }
        val filtered = sorted
            .filter { it.second == firstDividerPacket || it.second == secondDividerPacket }
        return filtered
            .map { it.first + 1 }
            .reduce { a, b -> a * b }
    }
}
