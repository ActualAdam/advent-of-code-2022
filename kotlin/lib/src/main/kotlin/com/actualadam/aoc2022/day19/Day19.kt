package com.actualadam.aoc2022.day19

import com.actualadam.aoc2022.day19.Day19.Resource.*

object Day19 {
    // scv good to go, sir
    data class Blueprint(
        val id: Int,
        val robotPrices: Map<Resource, Bank>
    ) {
        companion object {
            fun from(string: String): Blueprint {
                val split = string.split(" ")
                val id = split[1].dropLast(1).toInt()
                val oreRobotPrice = Bank(
                    ore = split[6].toInt(),
                )
                val clayRobotPrice = Bank(
                    ore = split[12].toInt(),
                )
                val obsidianRobotPrice = Bank(
                    ore = split[18].toInt(),
                    clay = split[21].toInt(),
                )
                val geodeRobotPrice = Bank(
                    ore = split[27].toInt(),
                    obsidian = split[30].toInt(),
                )
                return Blueprint(
                    id,
                    robotPrices = mapOf(
                       ore to oreRobotPrice,
                       clay to clayRobotPrice,
                       obsidian to obsidianRobotPrice,
                       geode to geodeRobotPrice,
                    )
                )
            }
        }
    }

    data class Bank(
        var ore: Int = 0,
        var clay: Int = 0,
        var obsidian: Int = 0,
        var geode: Int = 0,
    ) {
        fun debit(other: Bank) {
            ore -= other.ore
            clay -= other.clay
            obsidian -= other.obsidian
            geode -= other.geode
        }

        fun credit(other: Bank) {
            ore += other.ore
            clay += other.clay
            obsidian += other.obsidian
            geode += other.geode
        }
    }

    data class Inventory(
        val blueprint: Blueprint,
        val resources: Bank = Bank(),
        val robots: Bank = Bank(ore = 1),
    ) {
        fun purchaseRobot(type: Resource) {
            val price = blueprint.robotPrices[type]!!
            resources.debit(price)
        }

        private fun canAffordRobot(type: Resource): Boolean {
            val price = blueprint.robotPrices[type]!!
            return resources.ore >= price.ore &&
                    resources.clay >= price.clay &&
                    resources.obsidian >= price.obsidian &&
                    resources.geode >= price.geode
        }

        fun clearRobotPurchase(type: Resource?): Resource? =
            if (type == null) null
            else if (canAffordRobot(type)) type else null

        fun collectResources() {
            // your bank of robots is the same as your capacity to collect since they all take 1 minute. gambling a little. please stay that way!
           resources.credit(robots)
        }

        fun addRobot(type: Resource) = when(type) {
            ore -> robots.ore += 1
            clay -> robots.clay += 1
            obsidian -> robots.obsidian += 1
            geode -> robots.geode += 1
        }
    }

    data class Amount(
        var quantity: Int,
        val resource: Resource,
    )

    enum class Resource {
        ore,
        clay,
        obsidian,
        geode,
        ;
    }

    class Simulation(
        blueprint: Blueprint,
        private val purchaseList: List<Resource>,
        private val minutes: Int
    ) {
        val inventory: Inventory = Inventory(blueprint)

        fun runSimulation() {
            val purchaseQueue = this.purchaseList.toMutableList()
            for(minute in 1..minutes) {
                // checking first, collecting, then executing purchase simulates the
                val purchaseCleared = inventory.clearRobotPurchase(purchaseQueue.firstOrNull())
                purchaseCleared?.let {
                    purchaseQueue.removeFirst()
                    inventory.purchaseRobot(it)
                }
                inventory.collectResources()
                purchaseCleared?.let {
                    inventory.addRobot(it)
                }
            }
        }
    }



    fun part1(lines: List<String>): Int {
        // todo: I grabbed this purchase list from the example execution in the problem statement. it needs to be generated based on the blueprint, optimizing for geode production.
        val purchaseList = listOf<Resource>(
            clay,
            clay,
            clay,
            obsidian,
            clay,
            obsidian,
            geode,
            geode,
        )

        val bluePrints = lines.map { Blueprint.from(it) }
        val simulations = bluePrints.map { Simulation(it, purchaseList, 24) }
        simulations.forEach { it.runSimulation() }
        // careful, simulations have states that survive the run. could have done this better.
        val endStates = simulations.map {it.inventory.copy()}
        val qualityLevels = endStates.map { it.blueprint.id * it.resources.geode }
        return qualityLevels.sum()
    }

    fun part2(lines: List<String>): Int {
        return 0
    }

}
