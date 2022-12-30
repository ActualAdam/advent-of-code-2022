package com.actualadam.aoc2022.day22

import com.actualadam.aoc2022.splitOn

object Day22 {
    /**
    * The board. 
    */
    data class Board(
        val tiles: List<Tile>,
        val instructions: List<Instruction>,
        val you: You,
        ) {

        fun moveYou(tiles: Int) {
            when(you.orientation) {
                Orientation.East, Orientation.West -> moveYouLat(tiles)
                Orientation.North, Orientation.South -> moveYouLong(tiles)
            }
        }

        fun moveYouLat(tiles: Int) {
            for (i in 1..tiles) {
                val nextTile = getNextLatTile()
                when (nextTile.type) {
                    TileType.Wall -> break
                    TileType.Open -> you.position = nextTile.position
                }
            }
        }

        fun moveYouLong(tiles: Int) {
            for (i in 1..tiles) {
                val nextTile = getNextLongTile()
                when (nextTile.type) {
                    TileType.Wall -> break
                    TileType.Open -> you.position = nextTile.position
                }
            }
        }

        fun getNextLatTile(): Tile {
            val tiles = this.tiles.filter { tile ->
                tile.position.y == you.position.y
            }
            val sortedTiles = when(you.orientation) {
                Orientation.East -> tiles.sortedBy { it.position.x }    
                Orientation.West -> tiles.sortedByDescending { it.position.x } 
                else -> throw IllegalStateException()
            }
            
            val curIdx = sortedTiles.indexOfFirst { it.position.x == you.position.x }

            return sortedTiles[(curIdx + 1).mod(tiles.size)]
        }

        fun getNextLongTile(): Tile {
            val tiles = this.tiles.filter { tile: Tile ->
                tile.position.x == you.position.x
            }
            val sortedTiles = when(you.orientation) {
                Orientation.North -> tiles.sortedByDescending { it.position.y }
                Orientation.South -> tiles.sortedBy { it.position.y }
                else -> throw IllegalStateException()
            }
            
            val curIdx = sortedTiles.indexOfFirst { it.position.y == you.position.y }

            return sortedTiles[(curIdx + 1).mod(tiles.size)]
        }

        companion object {
            /**
            * Parse from string
            */
            fun from(lines: List<String>): Board {
                val (tilesStr, instructionsStr) = lines.splitOn { it.isEmpty() }

                val instructions = instructionsStr.single().split("(?<=[RL])".toRegex()).dropLast(1).map { Instruction.from(it) }
                 
                val tiles = tilesStr.mapIndexed { y, row ->
                    row.mapIndexedNotNull { x, char ->
                        if (char == ' ') null
                        else {
                            Tile(
                                    position = Position(x + 1, y + 1),
                                    type = TileType.from(char),
                                )
                        }
                    }
                }.flatten()

                val startingPosition = Position(
                    x = tiles.filter { it.position.y == 1 }.minBy { it.position.x }.position.x,
                    y = 1
                )

                println("Starting position is: $startingPosition")
                return Board(
                    tiles = tiles,
                    instructions = instructions,
                    you = You(position = startingPosition)
                )
            }
        }
    }

    data class You(
        var position: Position = Position(1,1),
        var orientation: Orientation = Orientation.East,
        ) {

        fun turn(direction: Direction) {
            fun newOrientation(direction: Direction) = 
                when(direction) {
                    Direction.L -> orientation.turnLeft()
                    Direction.R -> orientation.turnRight()
                }
            orientation = newOrientation(direction)
        }
    }

    data class Instruction(
        val advance: Int,
        val turn: Direction, 
        ) {
        companion object {
            fun from(string: String): Instruction {
                val advance = string.dropLast(1).toInt()
                val turn = string.takeLast(1)
                return Instruction(advance, Direction.valueOf(turn))
            }
        } 
    }


    enum class Direction {
        R,
        L,
        ;
    }

    enum class Orientation(val facingValue: Int) {
        North(3){
            override fun turnLeft() = West
            override fun turnRight() = East
        },
        East(0){
            override fun turnLeft() = North
            override fun turnRight() = South
        },
        South(1){
            override fun turnLeft() = East
            override fun turnRight() = West
        },
        West(2){
            override fun turnLeft() = South
            override fun turnRight() = North
        },
        ;

        abstract fun turnLeft(): Orientation
        abstract fun turnRight(): Orientation
    }

    data class Position(
        val x: Int = 1,
        val y: Int = 1,
    )

    data class Tile(
        val position: Position,
        val type: TileType,
    )

    enum class TileType(val display: Char) {
        Open('.'),
        Wall('#'),
        ;

        companion object {
            private val byDisplay = TileType.values().associateBy { it.display }
            fun from(display: Char): TileType = byDisplay[display]!!
        }
    }

    fun part1(lines: List<String>): Int {
        val board = Board.from(lines)

        fun performInstruction(board: Board, instruction: Instruction) {
            board.moveYou(instruction.advance)
            board.you.turn(instruction.turn)
        }
        board.instructions.forEach {
            println(it)
            performInstruction(board, it)
            println(board.you.position)
            println(board.you.orientation)
            println()
        }

        val password =
            1000 * board.you.position.y + 4 * board.you.position.x + board.you.orientation.facingValue
       return password
    }

    fun part2(lines: List<String>): Int {
        return 0
    }
}
