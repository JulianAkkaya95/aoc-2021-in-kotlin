import day04.domain.BingoGrid
import kotlin.streams.toList

fun main() {

    fun setupBingoNumbers(input: MutableList<String>): List<Int> {
        val bingoNumbersAsString = input.removeFirst()

        return bingoNumbersAsString.split(",").stream().map { it.toInt() }.toList()
    }

    fun createBoards(input: List<String>): MutableList<BingoGrid> {
        val bingoGrids: MutableList<BingoGrid> = mutableListOf()
        val gridNumbers = input.stream().filter { it.isNotEmpty() }.toList().toMutableList().map {
            it.split(" ").stream().filter { it.isNotEmpty() }.map { it.toInt() }.toList().toMutableList()
        }.toMutableList()


        while (gridNumbers.isNotEmpty()) {
            gridNumbers.take(5).let {
                gridNumbers.removeAll(it)
                bingoGrids.add(BingoGrid(it))

            }
        }
        bingoGrids.forEach {
            println(it.gridNumbers)
        }
        return bingoGrids
    }

    fun part2(bingoNumbers: List<Int>, input: List<String>): Long {
        val bingoGrids = createBoards(input)


        return 0L
    }


    val testInput = readInput("day04/Day04_test").toMutableList()
    val testBingoNumbers = setupBingoNumbers(testInput)

    //check(part1(testBingoNumbers, testInput) == 4512L)
    //println(part2(testBingoNumbers, testInput))
    check(part2(testBingoNumbers, testInput) == 1924L)

    //val input = readInput("Day04/Day04").toMutableList()
    //val bingoNumbers = setupBingoNumbers(input)
    //println(part1(bingoNumbers, input))
    //println(part2(bingoNumbers, input))

}
