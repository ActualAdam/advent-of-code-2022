package com.actualadam.aoc2022.day10

import com.actualadam.aoc2022.day10.Day10.PixelState.dark
import com.actualadam.aoc2022.day10.Day10.PixelState.lit

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
    class CPU(
        val crt: CRT
    ) {
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
                crt.drawPixel(cycle, registerX)
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
        val crt = CRT()
        val cpu = CPU(crt)
        instructions.forEach { cpu.applyInstruction(it) }
        return cpu.signalStrengths.values.sum()
    }

    enum class PixelState(val displayValue: Char) {
        lit('#'),
        dark('.'),
        ;
    }
    class CRT {
        val width = 40
        val height = 6
        val pixels: List<MutableList<PixelState?>> = listOf(
            arrayOfNulls<PixelState>(width).toMutableList(),
            arrayOfNulls<PixelState>(width).toMutableList(),
            arrayOfNulls<PixelState>(width).toMutableList(),
            arrayOfNulls<PixelState>(width).toMutableList(),
            arrayOfNulls<PixelState>(width).toMutableList(),
            arrayOfNulls<PixelState>(width).toMutableList(),
        )

        fun spritePosition(registerValue: Int): IntRange = IntRange(registerValue - 1, registerValue + 1)


        fun gunPosition(cpuCycle: Int): Pair<Int, Int> {
            val x = (cpuCycle - 1) % width
            val y = (cpuCycle - 1) / width
            return Pair(x, y)
        }

        fun drawPixel(cpuCycle: Int, registerValue: Int) {
            val sprite = spritePosition(registerValue)
            val gun = gunPosition(cpuCycle)
            if (sprite.contains(gun.first)) {
                pixels[gun.second][gun.first] = lit
            } else {
                pixels[gun.second][gun.first] = dark
            }
        }

        fun displayPixels(): List<String> {
            return pixels.map { row ->
                row.map { it!!.displayValue }.toString()
            }.onEach(::println)
        }
    }
    fun part2(input: List<String>): List<String> {
        val crt = CRT()
        val cpu = CPU(crt)
        input.forEach { cpu.applyInstruction(Instruction.from(it)) }
        return crt.displayPixels()
    }

}
