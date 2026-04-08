package aoc2025.solutions

object Day08 : Day {
    override fun solvePartOne(input: List<String>): Number {
        val (distances, _) = setup(input)
        val circuits = mutableListOf<MutableSet<Point>>()
        distances.take(10)
            .forEach { (_, p1, p2) ->
                addPointsToCircuits(circuits, p1, p2)
            }
        circuits.sortByDescending { it.size }
        return circuits
            .take(3).fold(1L) { sum, set -> sum * set.size }
    }

    override fun solvePartTwo(input: List<String>): Number {
        val (distances, numberOfItems) = setup(input)
        val circuits = mutableListOf<MutableSet<Point>>()
        var x1 = 0
        var x2 = 0

        for ((_, p1, p2) in distances) {
            addPointsToCircuits(circuits, p1, p2)
            x1 = p1.x
            x2 = p2.x
            if (circuits.size == 1 && circuits[0].size == numberOfItems){
                break
            }
        }
        return x1.toLong() * x2.toLong()
    }

    private fun setup(input: List<String>) = input
        .map { convertToPoint(it) }
        .let { points -> generateDistanceMap(points) to points.size }

    private fun addPointsToCircuits(
        circuits: MutableList<MutableSet<Point>>,
        p1: Point,
        p2: Point
    ) {
        val firstSet = circuits.find { p1 in it }
        val secondSet = circuits.find { p2 in it }
        when {
            firstSet != null && secondSet != null && firstSet !== secondSet -> {
                firstSet.addAll(secondSet)
                circuits.remove(secondSet)
            }
            firstSet != null -> firstSet.add(p2)
            secondSet != null -> secondSet.add(p1)
            else -> circuits.add(mutableSetOf(p1, p2))
        }
    }

    private fun generateDistanceMap(points: List<Point>) = buildList {
        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                add(Triple(calculateDistanceBetween(points[i], points[j]),points[i], points[j]))
            }
        }
    }.sortedBy { it.first }

    private fun convertToPoint(inputLine: String): Point {
        return inputLine.split(",")
            .map { it.toInt() }
            .let { (x, y, z) -> Point(x, y, z) }
    }

    private fun calculateDistanceBetween(p1: Point, p2: Point): Long {
        val dx = (p1.x - p2.x).toLong()
        val dy = (p1.y - p2.y).toLong()
        val dz = (p1.z - p2.z).toLong()
        return dx * dx + dy * dy + dz * dz
    }
}
data class Point(val x: Int, val y: Int, val z: Int)
