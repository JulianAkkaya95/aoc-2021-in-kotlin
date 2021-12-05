import kotlin.streams.toList

fun main() {

    fun setupBingoNumbers(input: MutableList<String>): List<Int> {
        val bingoNumbersAsString = input.removeFirst()

        return bingoNumbersAsString.split(",").stream().map { it.toInt() }.toList()
    }

    fun createBoards(input: List<String>): MutableList<MutableList<MutableList<Int>>> {
        val columnsAsInt: MutableList<MutableList<Int>> =
            input.stream().filter { it.isNotEmpty() }.toList().toMutableList().map {
                it.split(" ").stream().filter { it.isNotEmpty() }.map { it.toInt() }.toList().toMutableList()
            }.toMutableList()

        val boards: MutableList<MutableList<MutableList<Int>>> = mutableListOf()

        while (columnsAsInt.isNotEmpty()) {
            columnsAsInt.let {
                boards.add(
                    mutableListOf(
                        it.removeFirst(),
                        it.removeFirst(),
                        it.removeFirst(),
                        it.removeFirst(),
                        it.removeFirst()
                    )
                )
            }
        }

        return boards
    }

    fun calculateGameResult(board: MutableList<MutableList<Int>>, lastNumber: Long, roundCounter: Long):Triple<Boolean, Long, Long> {
        println("lastNumber : " + lastNumber)
        var sum = 0L

        board.forEach { sum += it.sum()  }
        println("sum : " + sum)
        sum *= lastNumber


        // IsWinningBoard RoundCounter BoardResult
        return Triple(true, roundCounter, sum)
    }

    fun playBoard(inputBoard: MutableList<MutableList<Int>>, bingoNumbers: List<Int>): Triple<Boolean, Long, Long> {
        var board: MutableList<MutableList<Int>> = inputBoard


        for (i in bingoNumbers.indices) {
            board = board.map { column ->
                column.stream()
                    .filter { number -> number != bingoNumbers[i] }.toList().toMutableList()
            }.toMutableList()

            board.forEach { column ->
                if(column.isEmpty()) {
                    return calculateGameResult(board, bingoNumbers[i].toLong(), i.toLong())
                }
            }
        }

        // IsWinningBoard RoundCounter BoardResult
        return Triple(false, 0L, 0L)
    }

    fun findWinnerSum(winningGames: MutableList<Triple<Boolean, Long, Long>>): Long {
        return winningGames.minByOrNull { it.second }!!.third
    }

    fun findLoosingSum(winningGames: MutableList<Triple<Boolean, Long, Long>>): Long {
        println(winningGames)
        return winningGames.maxByOrNull { it.second }!!.third
    }

    fun part1(bingoNumbers: List<Int>, input: List<String>): Long {
        // RoundCounter BoardResult
        val winningGames: MutableList<Triple<Boolean, Long, Long>> = mutableListOf()

        val boards = createBoards(input)


        boards.forEach { board ->
            val gameResult = playBoard(board, bingoNumbers)
            if (gameResult.first) {
                winningGames.add(gameResult)
            }
        }

        return findWinnerSum(winningGames)
    }

    fun part2(bingoNumbers: List<Int>, input: List<String>): Long {
        // RoundCounter BoardResult
        val winningGames: MutableList<Triple<Boolean, Long, Long>> = mutableListOf()

        val boards = createBoards(input)


        boards.forEach { board ->
            val gameResult = playBoard(board, bingoNumbers)
            if (gameResult.first) {
                winningGames.add(gameResult)
            }
        }

        return findLoosingSum(winningGames)
    }


    val testInput = readInput("Day04_test").toMutableList()
    val testBingoNumbers = setupBingoNumbers(testInput)

    //check(part1(testBingoNumbers, testInput) == 4512L)
    println(part2(testBingoNumbers, testInput))
    //check(part2(testBingoNumbers, testInput) == 1924L)

    val input = readInput("Day04").toMutableList()
    val bingoNumbers = setupBingoNumbers(input)
    //println(part1(bingoNumbers, input))
    //println(part2(bingoNumbers, input))

}
