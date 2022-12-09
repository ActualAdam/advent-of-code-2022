package com.actualadam.aoc2022.day09

import com.actualadam.aoc2022.day09.Day09Iteration2.Direction.*
import kotlin.math.abs

object Day09Iteration2 {
    enum class Direction{
        U, D, L, R, ;
    }

    data class Motion(
        val direction: Direction,
        var distance: Int,
    ) {
        companion object {
            fun parse(input: String): Motion {
                val (direction, distance) = input.split(' ')
                return Motion(valueOf(direction), distance.toInt())
            }
        }
    }

    data class Position(
        val x: Int,
        val y: Int,
    ) {
        override fun toString(): String = "$x,$y"
    }

    class Node(
        private var position: Position = Position(0,0),
        private val head: Node? = null
    ) {
        private lateinit var tail: Node
        val visited = mutableSetOf<Position>()

        fun addTail(): Node {
            tail = Node(position, this)
            return tail
        }

        fun move(motion: Motion) {
            for (i in motion.distance downTo 1) {
                position = when (motion.direction) {
                    U -> Position(position.x, position.y - 1)
                    D -> Position(position.x, position.y + 1)
                    L -> Position(position.x - 1, position.y)
                    R -> Position(position.x + 1, position.y)
                }
                move()
            }
        }

        fun move() {
            head?.let { follow() }
            visited.add(position)
            if (::tail.isInitialized) {
                tail.move()
            }
        }

        private fun shouldFollow() =
            abs(head!!.position.x - position.x) > 1 || abs(head.position.y - position.y) > 1

        private fun follow() {
            if (!shouldFollow()) return
            val newY = when {
                position.y < head!!.position.y -> position.y + 1
                position.y > head.position.y -> position.y - 1
                else -> position.y
            }
            val newX = when {
                position.x < head.position.x -> position.x + 1
                position.x > head.position.x -> position.x - 1
                else -> position.x
            }
            position = Position(newX, newY)
        }
    }

    fun part1(input: List<String>): Int {
        val head = Node()
        val tail = head.addTail()
        input.map { Motion.parse(it) }.let {motions ->
            motions.forEach {
                head.move(it)
            }
        }
        val visited = tail.visited
        println(visited)
        return visited.count()
    }

    fun part2(input: List<String>): Int {
        val head = Node()
        var tail = head
        repeat(9) {
            tail = tail.addTail()
        }

        input.map { Motion.parse(it) }.let {motions ->
            motions.forEach {
                head.move(it)
            }
        }
        return tail.visited.count()
    }

}
