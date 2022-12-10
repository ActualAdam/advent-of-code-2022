package com.actualadam.aoc2022.day10

object Day10 {
    enum class InstructionName {
        addx, noop, ;
    }
    val cycleTime = mapOf(
        InstructionName.addx to 2,
        InstructionName.noop to 1,
    )
    data class Instruction(
        val name: InstructionName,
        val arg: Int? = null,
    ) {
        companion object {
            fun from(string: String): Instruction {
                val split = string.trim().split(' ')
                val name = InstructionName.valueOf(split[0])
                val arg = if (split.size == 2) {
                    split[1].toInt()
                } else null
                return Instruction(name, arg)
            }
        }
    }
    class Cpu() {
        var registerX = 1
        var cycle = 1
        val signalStrengths = mutableMapOf(
            20 to 0,
            60 to 0,
            100 to 0,
            140 to 0,
            180 to 0,
            220 to 0,
        )

        fun applyInstruction(instruction: Instruction) {
            for (i in cycleTime[instruction.name]!!  downTo 1) {
                if (signalStrengths.keys.contains(cycle)) {
                    signalStrengths[cycle] = registerX * cycle
                }
                cycle ++
            }

            when (instruction.name) {
                InstructionName.addx -> registerX += instruction.arg!!
                InstructionName.noop -> Unit
            }
        }
    }
    fun part1(input: List<String>): Int {
        val instructions = input.map { Instruction.from(it) }
        val cpu = Cpu()
        instructions.forEach { cpu.applyInstruction(it) }
        return cpu.signalStrengths.values.sum()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

}
