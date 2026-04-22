package aoc2025.solutions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day09Test {
    val testData =
        """
            7,1
            11,1
            11,7
            9,7
            9,5
            2,5
            2,3
            7,3
        """.trimIndent().lines()
    @Test
    fun solvePartOne() {
        val result = Day09.solvePartOne(testData)
        assertEquals(50L, result)
    }

}