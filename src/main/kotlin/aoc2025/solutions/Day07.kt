package aoc2025.solutions

/***
 *
 * Day 7 - Use fold function to track the position of the beams and a beam-split counter
 *         as we move through the lines
 *
 */


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
                (this + 1).takeIf { it in line.indices },
                        (this - 1).takeIf { it in line.indices }
                    )
            else -> listOf(this)
        }
    }

    override fun solvePartTwo(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}