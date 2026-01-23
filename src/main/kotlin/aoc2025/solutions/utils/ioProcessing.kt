package aoc2025.solutions.utils

import java.io.File

fun readInput(filename: String) : List<String> {
    return File("src/main/resources/$filename.txt")
        .readLines()
}