package aoc2025.solutions

/***
 *
 * Day 7 - Use fold function to track the position of the beams and a beam-split counter
 *         as we move through the lines
 *
 */


data class BeamState(val beams: Set<Int>, val counter: Int)
data class TimelineState(val timelinesAtIndex: Map<Int, Long>)

object Day07: Day {

    override fun solvePartOne(input: List<String>): Number {
        val startingIndex = input[0].indexOfFirst { it == 'S' }
        val beamState = BeamState(setOf(startingIndex), 0)

        val finalState = input.drop(1).fold(beamState) { currentState, line ->
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
        val startingIndex = input[0].indexOfFirst { it == 'S' }
        val state = TimelineState(mapOf(startingIndex to 1L))

        val finalState = input.drop(1).fold(state) { currentState, line ->
            val newMap = mutableMapOf<Int, Long>()
            currentState.timelinesAtIndex.forEach {
                when(line[it.key]) {
                    '^' -> {
                        if(it.key - 1 in line.indices)
                            newMap.merge(it.key - 1, it.value, Long::plus)
                        if(it.key + 1 in line.indices)
                            newMap.merge(it.key + 1, it.value, Long::plus)
                    }
                    else -> newMap[it.key] = (newMap[it.key]?.plus(it.value)) ?: it.value
                }
            }
            currentState.copy(timelinesAtIndex = newMap)
        }
        return finalState.timelinesAtIndex.values.sum()
    }
}