package com.actualadam.aoc2022.day08

object Day08 {
    fun part1(input: List<String>): Int {
        val grid = input.map {line ->
            line.trim().toCharArray().map {node ->
                node.digitToInt()
            }
        }
        val gridWidth = grid.size 
        val gridHeight = grid[0].size
        fun isPeripheral(row: Int, col: Int): Boolean = row == 0 || col == 0 || row == gridHeight - 1 || col == gridWidth - 1
        val visible = grid.mapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, treeHeight ->
                rowIndex == 0 || colIndex == 0 || rowIndex == gridHeight - 1 || colIndex == gridWidth - 1
                        ||
                (0 until colIndex).all { grid[rowIndex][it] < treeHeight } ||
                ((colIndex + 1) until gridWidth).all { grid[rowIndex][it] < treeHeight } ||
                (0 until rowIndex).all { grid[it][colIndex] < treeHeight } ||
                ((rowIndex + 1) until gridHeight).all { grid[it][colIndex] < treeHeight }
            }
        }
        return visible.flatten().count { it == true }
    }

    data class Grid(
        val nodes: List<List<Int>>
    ) {
        fun height() = nodes.size
        fun width() = nodes[0].size
        fun getRow(row: Int) = nodes[row]
        fun getCol(col: Int) = (0 until height()).map{ nodes[it][col] }
        fun lookLeft(row: Int, col: Int) = getRow(row).take(col ).reversed()
        fun lookRight(row: Int, col: Int) = getRow(row).takeLast(width() - 1 - col)
        fun lookUp(row: Int, col: Int) = getCol(col).take(row ).reversed()
        fun lookDown(row: Int, col: Int) = getCol(col).takeLast( height() - 1 - row)
        fun scenicScore(row: Int, col: Int): Int {
            val curHeight = nodes[row][col]
            fun checkVisibility(directionalView: List<Int>) =
                directionalView.takeWhile { it < curHeight }.count().let { visibleTrees ->
                    // if there's a tree blocking, that tree needs to be counted,
                    // if we can see all the way to the edge then the visibility number we have is good.
                    if(visibleTrees != directionalView.size) visibleTrees + 1 else visibleTrees
                }
            val vizLeft = checkVisibility(lookLeft(row, col))
            val vizUp = checkVisibility(lookUp(row, col))
            val vizDown = checkVisibility(lookDown(row, col))
            val vizRight = checkVisibility(lookRight(row, col))
            return vizLeft *
                    vizUp *
                    vizDown *
                    vizRight

        }
        companion object {
            fun parse(input: List<String>) = Grid(input.map {line ->
                line.trim().toCharArray().map {node ->
                    node.digitToInt()
                }
            })
        }
    }

    fun part2(input: List<String>): Int {
        val grid = Grid.parse(input)
        val scenicScores = grid.nodes.mapIndexed() { rowIndex, row ->
            List(row.size) { colIndex ->
                grid.scenicScore(rowIndex, colIndex)
            }
        }
        return scenicScores.maxOf { it.max() }

    }

}
