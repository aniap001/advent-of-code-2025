package aoc2025.solutions

object Day06 : Day {
    override fun solvePartOne(input: List<String>): Number {
        val operators = input.last().trim().split(Regex("""\s+"""))
        val listsOfNumbers = parseLines(input.dropLast(1))
        val results = mutableListOf<Long>()
        for (y in operators.indices) {
            var resultMultiply = 1L
            var resultAdd = 0L
            for (x in listsOfNumbers.indices) {
                when (operators[y]){
                    "+" -> resultAdd += listsOfNumbers[x][y]
                    "*" -> resultMultiply *= listsOfNumbers[x][y]
                }
            }
            results.add(maxOf(resultMultiply, resultAdd))
        }
        return results.sum()
    }

    override fun solvePartTwo(input: List<String>): Number {
        val columns = input.first().length

        val readNumbers = mutableListOf<Long>()
        val number = StringBuilder()
        val results = mutableListOf<Long>()
        for (y in columns-1 downTo 0) {

            for (x in input.indices) {
                val char = input[x].getOrNull(y) ?: ' '
                when (char) {
                    '+', '*' -> {
                        if (number.isNotEmpty()) {
                            readNumbers.add(number.toString().toLong())
                            number.clear()
                        }
                        val result = when (char) {
                            '+' -> readNumbers.sum()
                            else -> readNumbers.reduce { a, b -> a * b }
                        }
                        results.add(result)
                        readNumbers.clear()
                    }
                    ' ' -> {
                        if (number.isNotEmpty()) {
                            readNumbers.add(number.toString().toLong())
                            number.clear()
                        }
                    }
                    else -> {
                        number.append(char)
                    }
                }
            }
        }
        return results.sum()
    }

    private fun parseLines(input: List<String>): List<List<Int>> {
        return input.map { line ->
            line.trim().split(Regex("""\s+""")).map { it.toInt() }
        }
    }
}