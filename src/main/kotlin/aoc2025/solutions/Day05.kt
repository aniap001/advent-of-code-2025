package aoc2025.solutions

object Day05 : Day {

    override fun solvePartOne(input: List<String>): Number {
        val ranges = input.takeWhile { it.isNotBlank() }
                          .map {
                              val (start, end) = Regex("""(\d+)-(\d+)""").find(it)?.destructured
                                  ?: throw IllegalArgumentException("Invalid line format: $it")
                              start.toLong()..end.toLong() // returns LongRange(start, end)
                          }
        val ingredientIds = input.dropWhile { it.isNotBlank() }
                                .drop(1)
                                .asSequence()
                                .map(String::toLong)

        return ingredientIds.count { ingredientId ->
            ranges.any { range -> ingredientId in range }
        }
    }

    override fun solvePartTwo(input: List<String>): Number {
        TODO("Not yet implemented")
    }

}