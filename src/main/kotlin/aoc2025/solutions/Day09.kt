package aoc2025.solutions

import kotlin.math.abs

object Day09 : Day {
    override fun solvePartOne(input: List<String>): Number {
        val points = input.map { it.split(",").let { (x, y) -> x.toInt() to y.toInt() } }
        var maxRectangleSize = 0L
        points.forEachIndexed { i, p1 ->
            points.drop(i + 1).forEach { p2 ->
                val area = (abs(p1.first - p2.first) + 1).toLong() * (abs(p1.second - p2.second) + 1).toLong()
                if (area > maxRectangleSize) { maxRectangleSize = area }
            }
        }
        return maxRectangleSize
    }

    override fun solvePartTwo(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}