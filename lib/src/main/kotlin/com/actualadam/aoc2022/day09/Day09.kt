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
    fun part1(input: List<String>): Int {
        data class Position(
            val x: Int,
            val y: Int,
        )
        open class Head(
            var position: Position = Position(0,0),
        ) {
            fun move(direction: Char) =
                when(direction) {
                    'U' -> Position(position.x, position.y - 1)
                    'D' -> Position(position.x, position.y + 1)
                    'L' -> Position(position.x - 1, position.y)
                    'R' -> Position(position.x + 1, position.y)
                    else -> throw IllegalArgumentException("don't know how to move $direction.")
                }
        }
        class Tail(
            val visited: MutableSet<Position> = mutableSetOf()
        ) : Head() {
            fun isTouchingHead(head: Head) =
                abs(head.position.x - position.x) <= 1 && abs(head.position.y - position.y) <= 1

            // i need to assume head, tail, head, tail 1 distance each even when the head is going to move more than 1 unit in a command.
            fun moveTowardHead(head: Head): Position {
                if (isTouchingHead(head)) { return position }
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
                return Position(newX, newY)
            }
        }

        val motions = input.map { Motion.parse(it) }.toMutableList()
        val head = Head()
        val tail = Tail()

       motions.forEach { motion ->
           for (i in motion.distance downTo 1) {
               head.position = head.move(motion.direction)
               println("head:${head.position.x},${head.position.y}")
               tail.position = tail.moveTowardHead(head)
               println("tail:${tail.position.x},${tail.position.y}")
               tail.visited.add(tail.position)
           }
       }
        println(tail.visited)
        return tail.visited.count()
    }

    fun part2(input: List<String>): Int {
        return 0
    }
}
