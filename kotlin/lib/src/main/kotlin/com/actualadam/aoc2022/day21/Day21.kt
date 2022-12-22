package com.actualadam.aoc2022.day21

object Day21 {

    data class Monkey(
        val name: String,
        val value: Long?,
        val operation: Operation?,
    ) {
        companion object {
            fun from(string: String): Monkey {
                val split = string.split(" ")
                val name = split[0].dropLast(1)
                val value = split[1].toLongOrNull()
                val operation = if (value == null) Operation.from(split.drop(1)) else null
                return Monkey(name, value, operation)
            }
        }
    }

    data class Operation(
        val left: String,
        val right: String,
        val function: (Long, Long) -> Long,
    ) {
        companion object {
            fun from(strings: List<String>): Operation {
               return Operation(
                   left = strings[0],
                   right = strings[2],
                   function = functionFromOperatorChar(strings[1].single())
               )
            }
            fun functionFromOperatorChar(char: Char): (Long, Long) -> Long =
                when(char) {
                    '+' -> { a,b -> a + b }
                    '-' -> { a,b -> a - b }
                    '*' -> { a,b -> a * b }
                    '/' -> { a,b -> a / b }
                    else -> throw IllegalArgumentException("not a supported operator: $char")
                }
        }
    }
    data class MonkeyTree(
        val monkeys: List<Monkey>,
    ) {
        val monkeysByName = monkeys.associateBy { it.name }

        fun resolveValue(monkeyName: String = "root"): Long {
            val cur = monkeysByName[monkeyName]!!
            return if (cur.value == null) {
                val operation = cur.operation!!
                val left = resolveValue(operation.left)
                val right = resolveValue(operation.right)
                operation.function(left, right)
            } else cur.value
        }

        companion object {
            fun from(lines: List<String>): MonkeyTree =
                MonkeyTree(lines.map { Monkey.from(it) })
        }


    }
    fun part1(lines: List<String>): Long {
        val monkeyTree = MonkeyTree.from(lines)
        return monkeyTree.resolveValue()
    }
    fun part2(lines: List<String>): Long {
        val humanInput = 3_305_669_217_840L
        val monkeyTree = MonkeyTree.from(lines).monkeys.let {
            MonkeyTree(it.map { if (it.name == "humn") it.copy(value = humanInput) else it })
        }

        val root = monkeyTree.monkeysByName["root"]!!
        val leftResult = monkeyTree.resolveValue(root.operation!!.left)
        val rightResult = monkeyTree.resolveValue(root.operation.right)
        println(leftResult)
        println(rightResult)
        return 0
    }
}
