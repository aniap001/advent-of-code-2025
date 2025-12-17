package org.example.solutions

import java.io.File

/**

Grids and neighbors problem

 **/
object Day4 {

    private const val ROLL = '@'
    private const val FILENAME = "day4"

    fun solvePartOne(): Int {
        val grid = readDiagram()
        var rollsCounter = 0
        // for each item check the 8 adjacent positions
        for (row in grid.indices) {
            for (column in grid[row].indices) {
                if (grid[row][column] == ROLL && findAdjacentRolls(row, column, grid) < 4)
                    rollsCounter++
            }
        }
        return rollsCounter
    }

    private fun findAdjacentRolls(row: Int, column: Int, grid: Array<CharArray>): Int {
        var counter = 0
        for ((directionR, directionC) in directions8) {
            val neighbourR = row + directionR
            val neighbourC = column + directionC
            if (isInBounds(neighbourR, neighbourC, grid)  &&
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
        grid: Array<CharArray>
    ): Boolean =
        row in grid.indices && column in grid[0].indices


    private fun readDiagram(): Array<CharArray> =
        File("src/main/resources/$FILENAME.txt")
            .readLines()
            .map { it.toCharArray() }
            .toTypedArray()
}