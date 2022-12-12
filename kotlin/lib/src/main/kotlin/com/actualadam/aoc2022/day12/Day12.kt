package com.actualadam.aoc2022.day12

object Day12 {
    data class Position(
        val x: Int,
        val y: Int,
    )

    data class Grid(val data: List<List<Char>>) {
        val adjacencyList: Map<Position, List<Position?>>
        init {
            val maxY = data.size - 1
            val maxX = data.first().size - 1
            adjacencyList = data.mapIndexed { y, row ->
                List(row.size) { x ->
                    Position(x, y) to listOf(
                        if (x != 0) Position(x - 1, y) else null,
                        if (x != maxX) Position(x + 1,y) else null,
                        if (y != 0) Position(x, y - 1) else null,
                        if (y != maxY) Position(x, y + 1) else null,
                    ).filterNotNull() as List<Position>
                }
            }.flatten().toMap()
        }

        fun getHeight(pos: Position): Char = data[pos.y][pos.x]

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
        while (queue.any()) {
            val currentNode = queue.removeFirst()
            if (visited.contains(currentNode)) continue
            visited.add(currentNode)
            val currentHeight = grid.getHeight(currentNode)
            if (currentHeight == 'E') break

            grid.adjacencyList[currentNode]!!.forEach {
                val nextHeight = grid.getHeight(it!!)
                if (currentHeight == 'S' || nextHeight <= currentHeight + 1) {
                    queue.add(it!!)
                }
            }
        }

        return visited.count()
    }

    fun part2(lines: List<String>): Int {
        return 0
    }
}
