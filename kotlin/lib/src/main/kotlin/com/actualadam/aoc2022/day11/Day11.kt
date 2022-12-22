package com.actualadam.aoc2022.day11

import com.actualadam.aoc2022.splitOn

object Day11 {
    data class Toss(
        val item: Int,
        val targetMonkey: Int,
    )

    data class Monkey(
        val id: Int,
        val items: ArrayDeque<Int>,
        var inspected: Int,
        val operation: (Int, Int) -> Int,
        val rightOperand: Int?,
        val testDivisor: Int,
        val testTrueMonkeyId: Int,
        val testFalseMonkeyId: Int,
    ) {
        fun takeTurn(extremelyWorried: Boolean = false): List<Toss> {
            val tosses = mutableListOf<Toss>()
            while (items.any()) {
                inspected++
                val oldWorryLevel = items.removeFirst()
                val opApplied = rightOperand?.let{
                    operation(oldWorryLevel, rightOperand)
                    operation(1,2)
                } ?: operation(oldWorryLevel, oldWorryLevel)
                val newWorryLevel = if (extremelyWorried) { opApplied } else { opApplied / 3 }
                val targetMonkey = if (newWorryLevel % testDivisor == 0) testTrueMonkeyId else testFalseMonkeyId
                tosses.add(Toss(newWorryLevel, targetMonkey))
            }
            return tosses.toList()
        }

        companion object {
            fun functionFromOperatorChar(char: Char): (Int, Int) -> Int =
               when(char) {
                   '+' -> { a,b -> a + b }
                   '-' -> { a,b -> a - b }
                   '*' -> { a,b -> a * b }
                   '/' -> { a,b -> a / b }
                   else -> throw IllegalArgumentException("not a supported operator: $char")
               }



            fun from(input: List<String>): Monkey {
                val idLine = input[0]
                val id = idLine.split(" ")[1].takeWhile { it != ':' }.toInt()
                val itemsLine = input[1].trimIndent()
                val items = itemsLine.split(":")[1].trim().split(", ").map { it.toInt() }
                val opLine = input[2].trimIndent()
                val rightOperandString = opLine.split(" ").last().trim()
                val operatorChar = opLine.split(" ").takeLast(2).first().trim().single()
                val fullFunction = functionFromOperatorChar(operatorChar)
                fun <A, B, C> partial2(f: (A, B) -> C, a: A): (B) -> C {
                    return { b: B -> f(a, b) }
                }
//                val partialAppliedFunction = partial2(fullFunction, rightOperandString.toInt())

                val divisorLine = input[3].trimIndent()
                val divisor = divisorLine.split(" ")[3].trim().toInt()
                val trueLine = input[4].trimIndent()
                val trueMonkeyId = trueLine.split(" ").last().trim().toInt()
                val falseLine = input[5].trimIndent()
                val falseMonkeyId = falseLine.split(" ").last().trim().toInt()

                return Monkey(
                    id = id,
                    items = ArrayDeque(items),
                    inspected = 0,
                    operation = fullFunction,
                    rightOperand = if (rightOperandString == "old") { null } else  rightOperandString.toInt(),
                    testDivisor = divisor,
                    testTrueMonkeyId = trueMonkeyId,
                    testFalseMonkeyId = falseMonkeyId,

                )
            }
        }
    }

    fun monkeyBusiness(monkeys: List<Monkey>): Long {
        println("Inspection Counts:")
        monkeys.forEach { println("${it.id}: ${it.inspected}") }
        return monkeys
            .map { it.inspected.toLong() }
            .sortedDescending().take(2).reduce { acc, cur -> acc * cur }
    }


    fun part1(input: List<String>): Long {
        val monkeys = input.splitOn { it == "" }.map { Monkey.from(it) }
        val monkeysById = monkeys.associateBy { it.id }
        repeat(20) {
            monkeys.forEach { monkey ->
                monkey.takeTurn().forEach { toss ->
                    monkeysById[toss.targetMonkey]!!.items.addLast(toss.item)
                }
            }
        }
        return monkeyBusiness(monkeys)
    }

    fun part2(input: List<String>): Long {
        // this isn't correct yet.
        val monkeys = input.splitOn { it == "" }.map { Monkey.from(it) }
        val monkeysById = monkeys.associateBy { it.id }
        repeat(10000) {
            monkeys.forEach { monkey ->
                monkey.takeTurn(extremelyWorried = true).forEach { toss ->
                    monkeysById[toss.targetMonkey]!!.items.addLast(toss.item)
                }
            }
        }
        return monkeyBusiness(monkeys)
    }
}
