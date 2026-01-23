package aoc2025.solutions

object Day05 : Day {

    override fun solvePartOne(input: List<String>): Number {
        val ranges = readRanges(input)
        val ingredientIds = input.dropWhile { it.isNotBlank() }
                                .drop(1)
                                .asSequence()
                                .map(String::toLong)

        return ingredientIds.count { ingredientId ->
            ranges.any { range -> ingredientId in range }
        }
    }

    override fun solvePartTwo(input: List<String>): Number {
        val ranges = readRanges(input).sortedBy { it.first }
        val mergedRanges : MutableList<LongRange> = mutableListOf()
        mergedRanges.add(ranges.first())

        for (i in 1..<ranges.size) {
            if (ranges[i].first > mergedRanges.last().last + 1) {
                mergedRanges.add(ranges[i])
            } else {
                val updatedRange = mergedRanges.last().first..maxOf(mergedRanges.last().last, ranges[i].last)
                mergedRanges[mergedRanges.lastIndex] = updatedRange
            }
        }

        var count = 0L
        mergedRanges.forEach {
            val num = it.last - it.first + 1
            count += num
        }
        return count
    }

    private fun readRanges(input: List<String>) : List<LongRange> {
        return input.takeWhile { it.isNotBlank() }
                    .map {
                        val (start, end) = Regex("""(\d+)-(\d+)""").find(it)?.destructured
                            ?: throw IllegalArgumentException("Invalid line format: $it")
                        start.toLong()..end.toLong() // returns LongRange(start, end)
                    }
    }

}