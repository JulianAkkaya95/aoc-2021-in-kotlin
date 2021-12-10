package day07

import readInput
import kotlin.streams.toList

fun main() {
    fun part1(input: Array<Long>): Long {
        val fuelPerPosition = Array(input.size) { 0L }

        for (i in fuelPerPosition.indices) {
            for (j in 0 until input.size) {
                fuelPerPosition[i] += Math.abs(j - i) * input[j]
            }
        }

        return fuelPerPosition.minOrNull()!!
    }

    fun part2(input: Array<Long>): Long {
        val fuelPerPosition = Array(input.size) { 0L }

        for (i in fuelPerPosition.indices) {
            for (j in 0 until input.size) {
                val distance = Math.abs(i - j)
                val gauss = ((1 + distance) * distance) / 2
                fuelPerPosition[i] = fuelPerPosition[i] + gauss * input[j]
            }
        }

        //fuelPerPosition.forEach { println(it) }
        return fuelPerPosition.minOrNull()!!
    }


    val inputTest = readInput("day07/Day07_test")[0].split(",").stream().map { it.toInt() }.toList()
    val maxPositionTest = inputTest.maxOrNull()!!
    val horizontalTest = Array(maxPositionTest + 1) { 0L }
    inputTest.forEach {
        horizontalTest[it]++
    }

    check(part1(horizontalTest) == 37L)
    check(part2(horizontalTest) == 168L)

    val input = readInput("day07/Day07")[0].split(",").stream().map { it.toInt() }.toList()
    val maxPosition = input.maxOrNull()!!
    val horizontal = Array(maxPosition + 1) { 0L }
    input.forEach {
        horizontal[it]++
    }

    println(part1(horizontal))
    println(part2(horizontal))
}
