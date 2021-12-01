fun main() {
    fun part1(input: List<Long>): Long {
        var increaseCounter = 0L
        var depth: Long? = null

        input.forEach {
            if (depth != null && depth!! < it) {
                increaseCounter++
            }
            depth = it
        }

        return increaseCounter
    }

    fun part2(input: List<Long>): Long {
        val inputSums: MutableList<Long> = mutableListOf()
        for (i in 0 until input.size - 2) {
            inputSums.add((input[i] + input[i + 1] + input[i + 2]))
        }

        return part1(inputSums)
    }

    // test if implementation meets criteria from the description, like:
    val testInputPart1 = readInput("Day01_01_test").map { it.toLong() }
    val testInputPart2 = readInput("Day01_02_test").map { it.toLong() }
    check(part1(testInputPart1) == 7L)
    check(part2(testInputPart2) == 5L)

    val input = readInput("Day01").map { it.toLong() }
    println(part1(input))
    println(part2(input))
}
