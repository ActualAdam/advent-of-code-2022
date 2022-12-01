package com.actualadam.aoc2022

import java.io.File

object TestUtil {
    fun getInputString(path: String) =
        File(javaClass.getResource(path)!!.toURI()).readText().trim()

    fun getInputLines(path: String) =
        File(javaClass.getResource(path)!!.toURI()).readLines()


}
