package org.example.solutions

import java.io.File


/*
Assumption: Banks in the input have number of batteries bigger than 12
*/
object Day3 {
    fun solveDay3(nBatteries: Int): Long =
        readBanks("day3")
            .sumOf { bank -> findVoltageTwo(bank, nBatteries) }

    fun findVoltageTwo(bank: String, nBatteries: Int): Long {
        val joltage = StringBuilder()
        var start = 0

        for (remaining in nBatteries downTo 1) {

            //leave enough digits for the remaining batteries
            val end = bank.length - remaining

            var highestDigit = bank[start]
            var index = start

            for (i in start..end) {
                if (bank[i] > highestDigit) {
                    highestDigit = bank[i]
                    index = i
                }
            }

            joltage.append(highestDigit)
            start = index + 1
        }
        return joltage.toString().toLong()
    }

    fun readBanks(filename: String): List<String> =
        File("src/main/resources/$filename.txt")
            .readLines()

}