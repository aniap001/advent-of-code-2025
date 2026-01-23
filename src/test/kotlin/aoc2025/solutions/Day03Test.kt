package aoc2025.solutions

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day03Test {

    private val testInput = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
        """.trimIndent().lines()

    @Test
    fun solvePartOne() {
        val result = Day03.solvePartOne(testInput)
        Assertions.assertEquals(357, result)
    }

    @Test
    fun solvePartTwo() {
        val result = Day03.solvePartTwo(testInput)
        Assertions.assertEquals(3121910778619, result)
    }

}