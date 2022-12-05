package com.actualadam.aoc2022.day05

import com.actualadam.aoc2022.splitOn

object Day05 {
    data class Instruction(
        val quantity: Int,
        val from: Int,
        val to: Int,
    ) {
        companion object {
            fun parse(instructionLine: String) =
                instructionLine.split(" ").let {
                    Instruction(
                        quantity = it[1].toInt(),
                        from = it[3].toInt(),
                        to = it[5].toInt()
                    )
                }
        }
    }

    data class State(
        val stacks: Map<Int, MutableList<Char>>

    ) {
        fun applyInstruction(instruction: Instruction) {
            repeat(instruction.quantity) {
                val crate = stacks[instruction.from]!!.removeLast()
                stacks[instruction.to]!!.add(crate)
            }
        }

        fun applyInstruction2(instruction: Instruction) {
            val from = stacks[instruction.from]!!
            val quantity = instruction.quantity
            val old = from.takeLast(quantity)
            repeat(quantity) {
                from.removeLast()
            }
            stacks[instruction.to]!!.addAll(old)
        }

        companion object {
            fun parse(stateLines: List<String>): State {
                val count = stateLines.reversed().first().trim().split(" ").count()
                val stacks = mutableMapOf<Int, MutableList<Char>>()
                (1..count).forEach { stacks[it] = ArrayDeque() }
                stateLines.reversed().drop(1).chunked(4) { it[1] }

                val upsideDownRowsOfCrates = stateLines.reversed().drop(1).map { it.chunked(4) { it[1] } }
                upsideDownRowsOfCrates.forEach {
                    it.forEachIndexed { index, crate ->
                        if (crate.isLetter()) {
                            stacks[index + 1]!!.add(crate)
                        }
                    }
                }
                return State(stacks)
            }
        }
    }



    fun part1(lines: List<String>): String {
        val (stateLines, instructionLines) = lines.splitOn { it.isEmpty() }
        val instructions = instructionLines.map { Instruction.parse(it) }
        val state = State.parse(stateLines)
        instructions.forEach {
            state.applyInstruction(it)
        }

        return state.stacks.values.filterNot { it.isEmpty() }.map {it.last()}.joinToString("")

    }

    fun part2(lines: List<String>): String {
        val (stateLines, instructionLines) = lines.splitOn { it.isEmpty() }
        val instructions = instructionLines.map { Instruction.parse(it) }
        val state = State.parse(stateLines)
        instructions.forEach {
            state.applyInstruction2(it)
        }

        return state.stacks.values.filterNot { it.isEmpty() }.map {it.last()}.joinToString("")

    }

}
