package com.actualadam.aoc2022.day12

object Day12 {
    data class Position(
        val x: Int,
        val y: Int,
    )

    data class Grid(val data: List<List<Char>>) {
        fun getAdjacent(position: Position): List<Position> {
            val maxY = data.size - 1
            val maxX = data.first().size - 1
            val (x, y) = position
            return listOfNotNull(
                if (x != 0) Position(x - 1, y) else null,
                if (x != maxX) Position(x + 1, y) else null,
                if (y != 0) Position(x, y - 1) else null,
                if (y != maxY) Position(x, y + 1) else null,
            )
        }

        fun getHeight(pos: Position): Char = data[pos.y][pos.x].let {
            if (it == 'S') (Char('z'.code + 1)) else it
        }

        fun findStart(): Position {
            data.forEachIndexed {y, row ->
                row.forEachIndexed {x, char ->
                    if (char =='S') {
                        return Position(x, y)
                    }
                }
            }
            throw IllegalStateException()
        }

        companion object {
            fun from(lines: List<String>) = Grid(lines.map{ it.toList() })
        }
    }

    fun part1(lines: List<String>): Int {
        // watch me stagger bloodily through breadth-first search
        val grid = Grid.from(lines)
        val visited = mutableSetOf<Position>()
        val start = grid.findStart()
        val queue = mutableListOf<Position>()
        queue.add(start)
        visited.add(start)
        while (queue.any()) {
            val currentNode = queue.removeFirst()
            val currentHeight = grid.getHeight(currentNode)
//            if (currentHeight == 'E') break

            val adjacent = grid.getAdjacent(currentNode)
            if (adjacent.any {
                grid.getHeight(it) == 'E'
            }) break
            adjacent.forEach {
                val nextHeight = grid.getHeight(it)
                if (nextHeight <= currentHeight + 1 && !visited.contains(it)) {
                    queue.add(it)
                    visited.add(it)
                }
            }
        }

        return visited.count()
    }


    fun part2(lines: List<String>): Int {
        return 0
    }
}
