package com.actualadam.aoc2022.day09

import kotlin.math.abs

object Day09 {
    data class Motion(
        val direction: Char,
        var distance: Int,
    ) {
        companion object {
            fun parse(input: String): Motion {
                val (direction, distance) = input.split(' ')
                return Motion(direction.single(), distance.toInt())
            }
        }
    }

    data class Position(
        val x: Int,
        val y: Int,
    )
    open class Head(
        var position: Position = Position(0,0),
    ) {
        var tail: Tail? = null
        fun move(direction: Char) {
            position = when (direction) {
                'U' -> Position(position.x, position.y - 1)
                'D' -> Position(position.x, position.y + 1)
                'L' -> Position(position.x - 1, position.y)
                'R' -> Position(position.x + 1, position.y)
                else -> throw IllegalArgumentException("don't know how to move $direction.")
            }
            tail?.moveTowardHead()
            tail?.visited?.add(tail!!.position)
        }
    }
    class Tail(
        val visited: MutableSet<Position> = mutableSetOf(),
        val head: Head,
    ) : Head() {
        fun isTouchingHead() =
            abs(head.position.x - position.x) <= 1 && abs(head.position.y - position.y) <= 1

        // i need to assume head, tail, head, tail 1 distance each even when the head is going to move more than 1 unit in a command.
        fun moveTowardHead() {
            if (isTouchingHead()) { return }
            val newY = when {
                position.y < head.position.y -> position.y + 1
                position.y > head.position.y -> position.y - 1
                else -> position.y
            }
            val newX = when {
                position.x < head.position.x -> position.x + 1
                position.x > head.position.x -> position.x - 1
                else -> position.x
            }
            position = Position(newX, newY)
            tail?.moveTowardHead()
            tail?.visited?.add(tail!!.position)
        }
    }

    fun part1(input: List<String>): Int {
        val motions = input.map { Motion.parse(it) }.toMutableList()
        val head = Head()
        val tail = Tail(head = head)
        head.tail = tail

        motions.forEach { motion ->
            for (i in motion.distance downTo 1) {
                head.move(motion.direction)
            }
        }
        println(tail.visited)
        return tail.visited.count()
    }

    fun part2(input: List<String>): Int {
        val motions = input.map { Motion.parse(it) }
        val head = Head()
        val one = Tail(head = head)
        head.tail = one
        val two = Tail(head = one)
        one.tail = two
        val three = Tail(head = two)
        two.tail = three
        val four = Tail(head = three)
        three.tail = four
        val five = Tail(head = four)
        four.tail = five
        val six = Tail(head = five)
        five.tail = six
        val seven = Tail(head = six)
        six.tail = seven
        val eight = Tail(head = seven)
        seven.tail = eight
        val nine = Tail(head = eight)
        eight.tail = nine

        motions.forEach { motion ->
            for (i in motion.distance downTo 1) {
                head.move(motion.direction)
            }
        }
        return nine.visited.count()
    }
}
