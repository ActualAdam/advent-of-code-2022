package com.actualadam.aoc2022.day13

import kotlinx.serialization.json.*

object Day13 {
    data class TreeNode<T: Comparable<T>>(
        val value: T,
        val children: MutableList<TreeNode<T>>? = null,
    ) {
        companion object {
            fun from(string: String): JsonArray = Json.parseToJsonElement(string).jsonArray
        }
    }

    private enum class TypeCase{
        BothIntegers,
        BothLists,
        OneIntegerOneList,
    }
    private fun getTypeCase(pair: Pair<JsonElement, JsonElement>): TypeCase =
        pair.toList().map {it::class.simpleName}.toSet().let {
            if (it.size == 2) {
                TypeCase.OneIntegerOneList
            } else {
            when(pair.first) {
                is JsonArray -> TypeCase.BothLists
                is JsonPrimitive -> TypeCase.BothIntegers
                else -> throw IllegalStateException("didn't plan for ${pair.first::class.simpleName}")
            }
        }
    }
    fun isInOrder(packets: Pair<JsonArray, JsonArray>): Boolean {
        val zipped = packets.first.zip(packets.second)
        fun loop(pairs: List<Pair<JsonElement, JsonElement>> = zipped): Boolean {
            for (pair in pairs) {
                if (pair.first is JsonPrimitive && pair.second is JsonPrimitive) {
                    val first = (pair.first as JsonPrimitive).content.toInt()
                    val second = (pair.second as JsonPrimitive).content.toInt()
                    if (first < second) return true
                    else if (first > second) return false
                    continue; //value are integers but they are the same
                } else if (pair.first is JsonArray && pair.second is JsonArray) {
                    val first = (pair.first as JsonArray)
                    val second = (pair.second as JsonArray)
                    return loop(first.zip(second))
                } else {
                    // todo: can I make a castIf function that takes a reference and a type and checks that the object is that type then returns a reference of that type, or null, or throw?
                    //todo: I don't know if that would work, and why it wouldn't already exist if that worked
                    val first = if (pair.first is JsonPrimitive) JsonArray(listOf(pair.first)) else pair.first as JsonArray
                    val second = if (pair.second is JsonPrimitive) JsonArray(listOf(pair.second)) else pair.second as JsonArray
                    return loop(first.zip(second))
                }
            }
            TODO("if we made it all the way through the zipped pairs, compare the size of the zipped pairs to the size of each list and act accordingly")
            /// I think I need the JsonElements all the way flattened to be able to say.

        }
        return loop()
    }

    fun part1(lines: List<String>) : Int {

        fun parse(string: String): JsonArray = Json.parseToJsonElement(string).jsonArray

        val packetPairs = lines.chunked(3).map { Pair(parse(it[0]), parse(it[1])) }

       return 0
    }
    fun part2(lines: List<String>) : Int {
        return 0
    }
}

abstract class TypedKey<V> {
}
