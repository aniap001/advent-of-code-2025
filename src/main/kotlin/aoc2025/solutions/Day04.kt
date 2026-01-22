package aoc2025.solutions

import java.io.File

/**

Grids and neighbors problem

 **/
object Day04 : Day {

    private const val EMPTY = '.'
    private const val ROLL = '@'
    private const val FILENAME = "day4"

    private val grid = readDiagram()

    override fun solvePartOne(): Int {
        var removedRolls = 0
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                if (grid[row][column] == ROLL && countAdjacentRolls(row, column) < 4)
                    removedRolls++
            }
        }
        return removedRolls
    }

    override fun solvePartTwo(): Int {
        var removedRollsTotal = 0
        do {
            var removedRollsThisRound = 0
            for (row in grid.indices) {
                for (column in grid[row].indices) {
                    if (grid[row][column] == ROLL && countAdjacentRolls(row, column) < 4) {
                        removedRollsThisRound++
                        grid[row][column] = EMPTY
                    }
                }
            }
            removedRollsTotal += removedRollsThisRound
        } while (removedRollsThisRound > 0)

        return removedRollsTotal
    }

    private fun countAdjacentRolls(row: Int, column: Int): Int {
        var counter = 0
        for ((directionR, directionC) in directions8) {
            val neighbourR = row + directionR
            val neighbourC = column + directionC
            if (isInBounds(neighbourR, neighbourC)  &&
                grid[neighbourR][neighbourC] == ROLL) {
                counter++
                if (counter == 4) return counter
            }
        }
        return counter
    }

    private val directions8 = listOf(
        -1 to -1, -1 to 0, -1 to 1,
        0 to -1,           0 to 1,
        1 to -1,  1 to 0,  1 to 1
    )

    private fun isInBounds(
        row: Int,
        column: Int,
    ): Boolean =
        row in grid.indices && column in grid[0].indices


    private fun readDiagram(): Array<CharArray> =
        File("src/main/resources/$FILENAME.txt")
            .readLines()
            .map { it.toCharArray() }
            .toTypedArray()
}