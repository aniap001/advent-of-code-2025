package aoc2025.solutions

import java.io.File

object Day05 : Day {

    override fun solvePartOne(): Number {
        val input = readInput()
        val ranges = input.takeWhile { it.isNotBlank() }
                          .map {
                              val (start, end) = it.split("-").map(String::toLong)
                              start..end // returns LongRange(start, end)
                          }
        val ingredientIds = input.dropWhile { it.isNotBlank() }
                                .drop(1)
                                .asSequence()
                                .map(String::toLong)

        return ingredientIds.count { ingredientId ->
            ranges.any { range -> ingredientId in range }
        }
    }

    override fun solvePartTwo(): Number {
        TODO("Not yet implemented")
    }


    private fun readInput() : List<String> {
        return File("src/main/resources/day5.txt")
            .readLines()
    }
}