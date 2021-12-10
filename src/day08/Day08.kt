package day08

import readInput
import kotlin.streams.toList

fun main() {
    val digits = mapOf(Pair("one", 2), Pair("four", 4), Pair("eight", 7), Pair("seven", 3))

    fun part1(input: List<String>): Long {

        println(input)
        var counter = 0L;
        input.forEach {
            if (digits.containsValue(it.length)) {
                counter++
                println(it)
            }

        }

        return counter
    }


    val inputTest = readInput("day08/Day08_test").stream().map { it.split(" | ").last() }.toList().joinToString(" ").split(" ")

    check(part1(inputTest) == 26L)
    //check(part2(horizontalTest) == 168L)

    val input = readInput("day08/Day08").stream().map { it.split(" | ").last() }.toList().joinToString(" ").split(" ")

    println(part1(input))
    //println(part2(horizontal))
}
