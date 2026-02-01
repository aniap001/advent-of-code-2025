package aoc2025.solutions

data class State(val beams: Set<Int>, val counter: Int)

object Day07: Day {

    override fun solvePartOne(input: List<String>): Number {
        val startingIndex = input[0].indexOfFirst { it == 'S' }
        val state = State(setOf(startingIndex), 0)

        val finalState = input.drop(1).fold(state) { currentState, line ->
            val splitsInThisStep = currentState.beams.count { line[it] == '^'}
            val nextBeams = currentState.beams.flatMap { it.changeBeamPositions(line) }.toSet()
            currentState.copy(
                beams = nextBeams,
                counter = currentState.counter + splitsInThisStep
            )
        }
        return finalState.counter
    }

    private fun Int.changeBeamPositions(line: String): List<Int>{
        return when(line[this]) {
            '^' -> listOfNotNull(
                        if (this + 1 in line.indices) this + 1 else null,
                        if (this - 1 in line.indices) this - 1 else null
                    )
            else -> listOf(this)
        }
    }

    override fun solvePartTwo(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}