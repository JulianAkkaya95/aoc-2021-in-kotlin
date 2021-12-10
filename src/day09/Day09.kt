package day09

import readInput

fun main() {

    fun part1(input: Array<Array<Int>>): Int {
        var resultCounter = 0;

        for (i in input.indices) {
            for (j in input[i].indices) {
                val locationHeight = input[i][j]
                val up: Int? = if (input.elementAtOrNull(i - 1) == null) null else input[i - 1].elementAtOrNull(j)
                val down: Int? = if (input.elementAtOrNull(i + 1) == null) null else input[i + 1].elementAtOrNull(j)
                val left: Int? = input[i].elementAtOrNull(j - 1)
                val right: Int? = input[i].elementAtOrNull(j + 1)

                if (up == null || up > locationHeight) {
                    if (down == null || down > locationHeight) {
                        if (left == null || left > locationHeight) {
                            if (right == null || right > locationHeight) {
                                resultCounter += locationHeight + 1
                            }
                        }
                    }
                }
            }
        }

        return resultCounter
    }

    val testMap: Array<Array<Int>>

    readInput("day09/Day09_test").let { input ->
        val mapYMax = input.size
        val mapXMax = input[0].length

        testMap = Array(mapYMax) { Array(mapXMax) { 0 } }

        for (i in testMap.indices) {
            for (j in testMap[i].indices) {
                testMap[i][j] = Character.getNumericValue(input[i][j])
            }
        }
    }

    check(part1(testMap) == 15)
    //check(part2(horizontalTest) == 168L)

    val map: Array<Array<Int>>
    readInput("day09/Day09").let { input ->
        val mapYMax = input.size
        val mapXMax = input[0].length

        map = Array(mapYMax) { Array(mapXMax) { 0 } }

        for (i in map.indices) {
            for (j in map[i].indices) {
                map[i][j] = Character.getNumericValue(input[i][j])
            }
        }
    }

    println(part1(map))
    //println(part2(horizontal))
}
