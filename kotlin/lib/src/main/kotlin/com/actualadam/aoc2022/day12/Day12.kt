package com.actualadam.aoc2022.day12

object Day12 {
    const val ARTIFICIAL_START_CHAR = 'a'
    const val ARTIFICIAL_END_CHAR = '{'
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
            when(it) {
                'S' -> ARTIFICIAL_START_CHAR
                'E' -> ARTIFICIAL_END_CHAR
                else -> it
            }
        }

        fun findStart(height: Char): List<PathNode> {
            val startNodes = mutableListOf<PathNode>()
            data.forEachIndexed {y, row ->
                row.forEachIndexed {x, char ->
                    if (char == height) {
                        startNodes.add(PathNode(Position(x, y), ARTIFICIAL_START_CHAR))
                    }
                }
            }
            return startNodes.toList()
        }

        companion object {
            fun from(lines: List<String>) = Grid(lines.map{ it.toList() })
        }
    }

    data class PathNode(
        val current: Position,
        val value: Char,
        val previous: Position? = null,
    )

    tailrec fun traceBackPath(visited: List<PathNode>, cur: PathNode, acc: List<Position> = listOf()): List<Position> {
        return if (cur.previous == null) {
            (acc).reversed()
        } else {
            traceBackPath(visited, visited.find {it.current == cur.previous}!!, acc + cur.current)
        }
    }

    fun shortestPath(grid: Grid, startNode: PathNode = grid.findStart('S').single()): List<Position> {
        // watch me stagger bloodily through breadth-first search
        val visited = mutableListOf<PathNode>()
        val queue = mutableListOf<PathNode>()
        queue.add(startNode)
        visited.add(startNode)
        while (queue.any()) {
            val currentNode = queue.removeFirst()
            val currentHeight = currentNode.value
            if (currentHeight == ARTIFICIAL_END_CHAR)
                return traceBackPath(visited, currentNode)

            val adjacent = grid.getAdjacent(currentNode.current)
            adjacent.forEach {nextPosition ->
                val nextHeight = grid.getHeight(nextPosition)
                val nextNode = PathNode(nextPosition, nextHeight, currentNode.current)
                if (nextHeight <= currentHeight + 1 && !visited.contains(nextNode)) {
                    queue.add(nextNode)
                    visited.add(nextNode)
                }
            }
        }
        throw IllegalStateException()
    }

    fun part1(inputLines: List<String>): Int {
        val grid = Grid.from(inputLines)
        val shortestPath = shortestPath(grid)
        return shortestPath.count()
    }


    fun part2(lines: List<String>): Int {
        val grid = Grid.from(lines)
        val startingPoints = grid.findStart('a')
        val pathSteps = startingPoints.mapNotNull {
            try {shortestPath(grid, it).count()}
                catch(e: IllegalStateException){ null}
        }.sorted()
        return pathSteps.first()

    }
}
