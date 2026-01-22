package aoc2025.solutions

import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs


object Day01 : Day {
    var dial = 50
    var password = 0

    override fun solvePartOne(): Int {

        val input = readInput("day1")
        input.forEach {
            val dir = it.first()
            val number = it.drop(1).toInt()

            when (dir) {
                'L' -> {
                    dial = ((dial - number) + 100) % 100
                }
                'R' -> {
                    dial = (dial + number) % 100
                }
            }
            if (dial == 0) password++
        }
        return password
    }

    override fun solvePartTwo(): Int {
        val input = File("day1.txt").readLines()

        input.forEach {
            val dir = it.first()
            val number = it.drop(1).toInt()

            when (dir) {
                'L' -> {
                    if (number > dial) {
                        password += (abs(dial - number) + 99) / 100
                        //need to subtract one in the case when the dial starts from position 0, we don't want to count the wrap
                        if (dial == 0) password--
                    }

                    dial = (((dial - number) % 100) + 100) % 100

                    if (dial == 0)
                        password++
                    println("L$number, dial points to $dial and counter: $password")
                }
                'R' -> {
                    val newPosition = dial + number
                    password += (newPosition / 100)

                    dial = (dial + number) % 100
                    println("R$number, dial points to $dial and counter: $password")
                }
            }
        }
        return password
    }

    private fun readInput(name: String) = Path("src/main/resources/$name.txt").readText().trim().lines()

}
