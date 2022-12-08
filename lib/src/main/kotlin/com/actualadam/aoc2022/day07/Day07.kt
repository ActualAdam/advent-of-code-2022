package com.actualadam.aoc2022.day07

object Day07 {
    fun part1(lines: List<String>): Int {
        val tree = buildTree(lines)
        tree.calculateSize()
        var dirSizes = mutableListOf<Int>()
        traverseDepthFirst(tree) {
            if (it.type == NodeType.Directory) {
                dirSizes.add(it.calculateSize())
            }
        }
        return dirSizes.filter { it < sizeLimit }.sum()
    }

    fun part2(lines: List<String>): Int {
        val total = 70_000_000
        val required = 30_000_000
        val tree = buildTree(lines)
        tree.calculateSize()
        var dirSizes = mutableListOf<Int>()
        traverseDepthFirst(tree) {
            if (it.type == NodeType.Directory) {
                dirSizes.add(it.size!!)
            }
        }
        val inUse = tree.size!!
        val free = total - inUse
        val target = required - free
        return dirSizes.filter { it > target}.min()

    }

    fun buildTree(inputLines: List<String>): Node {
        val root = Node(
            size = null,
            name = "/",
            type = NodeType.Directory,
            children = mutableListOf()
        )
        val dotdot: MutableList<Node> = mutableListOf(root)
        inputLines.filterNot {
            it.startsWith("$ ls") || it.startsWith("$ cd /")
        }.forEach { listing ->
            val listingTerms = listing.split(" ")
            when {
                listing.startsWith("dir") ->
                    dotdot.last().children!!.add(
                        Node(
                            size = null,
                            name = listingTerms[1],
                            type = NodeType.Directory,
                            children = mutableListOf(),
                        )
                    )

                listing.startsWith("$ cd") ->
                    if (listingTerms[2] == "..") {
                        dotdot.removeLast()
                    } else {
                        dotdot.add(
                            dotdot
                                .last()
                                .children!!
                                .find { it.name == listingTerms[2] }!!
                        )
                    }
                else ->
                    dotdot.last().children!!.add(
                        Node(
                            size = listingTerms[0].toInt(),
                            name = listingTerms[1],
                            type = NodeType.File,
                            children = null,
                        )
                    )
            }
        }
        return root
    }

    val sizeLimit: Int = 100000

    enum class NodeType() {
        File,
        Directory,
        ;
    }

    fun traverseDepthFirst(
        rootNode: Node,
        action: (Node) -> Unit
    ) {
        val stack = ArrayDeque<Node>()
        stack.addFirst(rootNode)

        while (stack.isNotEmpty()) {
            val currentNode = stack.removeFirst()

            action.invoke(currentNode)

            if (currentNode.children != null) {
                for (index in currentNode.children!!.size - 1 downTo 0) {
                    stack.addFirst(currentNode.children[index])
                }
            }
        }
    }


    data class Node(
        var size: Int?,
        val name: String,
        val type: NodeType,
        val children: MutableList<Node>?,
    ) {

        fun calculateSize(): Int =
            if (type == NodeType.File) {
                size!!
            } else {
                size = children!!.sumOf { it.calculateSize() }
                size!!
            }
    }

}
