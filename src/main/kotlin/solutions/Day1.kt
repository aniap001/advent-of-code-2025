package org.example.solutions

import kotlin.math.abs


object Day1 {
    var dial = 50
    var password = 0

    fun partOne(input: List<String>): Int {
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

    fun partTwo(input: List<String>): Int {
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
}
