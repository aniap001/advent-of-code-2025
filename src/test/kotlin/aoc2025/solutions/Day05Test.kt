package aoc2025.solutions

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day05Test {

    val testInput = """
        3-5
        10-14
        16-20
        12-18

        1
        5
        8
        11
        17
        32
    """.trimIndent().lines()


    @Test
    fun solvePartOne() {
        val result = Day05.solvePartOne(testInput)
        Assertions.assertEquals(3, result)
    }

}