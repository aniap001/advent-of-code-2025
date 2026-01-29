package aoc2025.solutions

object Day07: Day {
    override fun solvePartOne(input: List<String>): Number {
        val startingIndex = input[0].indexOfFirst { it == 'S' }
        var counter = 0
        val beamAt = BooleanArray(input[0].length) { false }
        beamAt[startingIndex] = true

        (1..<input.size).forEach { i ->
            val line = input[i]
            beamAt.forEachIndexed { index, _ ->
                if (line[index] == '^' && beamAt[index]) {
                    counter++
                    beamAt[index] = false
                    beamAt[index+1] = true
                    beamAt[index-1] = true
                }
            }

        }

        return counter
    }

    override fun solvePartTwo(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}