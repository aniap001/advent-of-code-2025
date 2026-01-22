package aoc2025.solutions

import java.io.File
import kotlin.math.pow

object Day02 : Day {

    override fun solvePartOne(): Long =
        readRanges("day2")
            .sumOf { (min, max) -> sumInvalidIds(min, max, ::isInvalidIdPartOne) }

    override fun solvePartTwo(): Long =
        readRanges("day2")
            .sumOf { (min, max) -> sumInvalidIds(min, max, ::isInvalidIdPartTwo) }

    fun sumInvalidIds(
        min: Long,
        max: Long,
        predicate: (Long) -> Boolean)
    : Long = (min..max)
        .asSequence()
        .filter(predicate)
        .sum()

    fun isInvalidIdPartOne(id: Long) : Boolean {
        val digits = id.digits()
        if (digits % 2 == 1) return false

        // e.g. for number 123123 I want to get 1000 to split number in two
        val half = 1L * tenPower(digits/2)

        val left = id / half
        val right = id % half

        return left == right
    }

    fun isInvalidIdPartTwo(id: Long) : Boolean {
        val digits = id.digits()
        if (digits == 1) return false

        // try all possible slicing factors
        for (i in 2..digits) {
            if (digits % i == 0) {
                val chunkSize = digits / i
                val parts = id.toString().chunked(chunkSize)
                if (parts.distinct().size == 1) return true //if all parts are identical the id is not valid
            }
        }
        return false
    }

    fun readRanges(filename: String): List<Pair<Long, Long>> =
        File("src/main/resources/$filename.txt")
            .readText()
            .trim()
            .split(",")
            .map { it.split("-").let { (a, b) -> a.toLong() to b.toLong() } }

    fun tenPower(n: Int): Long = 10.0.pow(n).toLong()

    fun Long.digits() = toString().length
}