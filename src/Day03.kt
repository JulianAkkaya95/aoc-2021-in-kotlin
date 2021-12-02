import kotlin.streams.toList

fun main() {

    fun part1(input: List<String>): Long {

        var gammaRate = ""
        var epsilonRate = ""

        for (i in 0 until input[0].length) {
            val bitAsChar =
                input.stream().map { it[i] }.toList().groupingBy { it }.eachCount().maxByOrNull { it.value }!!.key
            if (bitAsChar == '0') {
                gammaRate += "0"
                epsilonRate += "1"
            } else {
                gammaRate += "1"
                epsilonRate += "0"
            }
        }

        return Integer.parseInt(gammaRate, 2).toLong() * Integer.parseInt(epsilonRate, 2).toLong()
    }


    fun part2(input: List<String>): Long {

        var oxygenGeneratorRatingInput = input.toMutableList()
        var i = 0;
        while (oxygenGeneratorRatingInput.size != 1) {
            val counterMap =
                oxygenGeneratorRatingInput.stream().map { it[i] }.toList().groupingBy { it }.eachCount().toMutableMap()
            if (counterMap['1'] == null) counterMap['1'] = 0
            if (counterMap['0'] == null) counterMap['0'] = 0

            oxygenGeneratorRatingInput = if (counterMap['1']!! >= counterMap['0']!!) {
                oxygenGeneratorRatingInput.stream().filter { it[i] == '1' }.toList().toMutableList()
            } else {
                oxygenGeneratorRatingInput.stream().filter { it[i] == '0' }.toList().toMutableList()
            }
            i++
        }

        var co2ScrubberRating = input.toMutableList()
        i = 0;
        while (co2ScrubberRating.size != 1) {
            val counterMap = co2ScrubberRating.stream().map { it[i] }.toList().groupingBy { it }.eachCount()
            if (counterMap['1']!! < counterMap['0']!!) {
                co2ScrubberRating = co2ScrubberRating.stream().filter { it[i] == '1' }.toList().toMutableList()
            } else {
                co2ScrubberRating = co2ScrubberRating.stream().filter { it[i] == '0' }.toList().toMutableList()
            }
            i++
        }

        return Integer.parseInt(oxygenGeneratorRatingInput[0], 2).toLong() * Integer.parseInt(co2ScrubberRating[0], 2)
            .toLong()
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    check(part1(testInput) == 198L)
    check(part2(testInput) == 230L)

    println(part1(input))
    println(part2(input))
}
