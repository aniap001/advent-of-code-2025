package aoc2025.solutions

object Day07: Day {
    data class State(var beams: Set<Int>, var counter: Int)

    override fun solvePartOne(input: List<String>): Number {
        val startingIndex = input[0].indexOfFirst { it == 'S' }
        val state = State(mutableSetOf(startingIndex), 0)

        val finalState = input.drop(1).fold(state) { currentState, line ->
            val nextBeams = mutableSetOf<Int>()
            var counter = currentState.counter
            currentState.beams.forEach {
                if (line[it] == '^') {
                    counter++
                    if (it < line.length-1) nextBeams.add(it+1)
                    if (it > 0) nextBeams.add(it-1)
                } else {
                    nextBeams.add(it)
                }
            }
            State(nextBeams, counter)
        }

        return finalState.counter
    }

    override fun solvePartTwo(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}