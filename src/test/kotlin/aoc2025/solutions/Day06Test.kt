package aoc2025.solutions

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day06Test {

    val input = listOf(
        "123 328  51 64 ",
        " 45 64  387 23 ",
        "  6 98  215 314",
        "*   +   *   +  "
    )

    @Test
    fun solvePartOne() {
        val result = Day06.solvePartOne(input)
        Assertions.assertEquals(4277556L, result)
    }

    @Test
    fun solvePartTwo() {
        val result = Day06.solvePartTwo(input)
        Assertions.assertEquals(3263827L, result)

    }

}