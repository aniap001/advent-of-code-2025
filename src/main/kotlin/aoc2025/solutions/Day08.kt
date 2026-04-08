package aoc2025.solutions

object Day08 : Day {
    override fun solvePartOne(input: List<String>): Number {
        val points = input.map { convertToPoint(it) }
        val distances = generateDistanceMap(points)
        val circuits = mutableListOf<MutableSet<Point>>()
        distances.take(1000)
            .forEach { (_, p1, p2) ->
                val firstSet = circuits.find { it.contains(p1) }
                val secondSet = circuits.find { it.contains(p2) }
                when {
                    firstSet != null && secondSet != null && firstSet != secondSet -> {
                        firstSet.addAll(secondSet)
                        circuits.remove(secondSet)
                    }
                    firstSet == null && secondSet == null -> {
                        circuits.add(mutableSetOf(p1, p2))
                    }
                    firstSet != null && secondSet == null -> {
                        firstSet.add(p2)
                    }
                    firstSet == null && secondSet != null -> {
                        secondSet.add(p1)
                    }
                }
            }

        circuits.sortByDescending { it.size }
        return circuits
            .take(3).fold(1L) { sum, set -> sum * set.size }
    }

    override fun solvePartTwo(input: List<String>): Number {
        TODO("Not yet implemented")
    }

    private fun generateDistanceMap(points: List<Point>): List<Triple<Long, Point, Point>> {
        val results = mutableListOf<Triple<Long, Point, Point>>()

        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val p1 = points[i]
                val p2 = points[j]
                val distance = calculateDistanceBetween(p1, p2)
                results.add(Triple(distance,p1, p2))
            }
        }
        return results.sortedBy { it.first }
    }

    private fun convertToPoint(inputLine: String): Point {
        return inputLine.split(",").let {
            Point(it[0].toInt(), it[1].toInt(), it[2].toInt())
        }
    }

    private fun calculateDistanceBetween(p1: Point, p2: Point): Long {
        val dx = (p1.x - p2.x).toLong()
        val dy = (p1.y - p2.y).toLong()
        val dz = (p1.z - p2.z).toLong()
        return dx * dx + dy * dy + dz * dz
    }
}
data class Point(val x: Int, val y: Int, val z: Int)
