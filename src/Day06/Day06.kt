package Day06

import Day06.Domain.LanternFish
import readInput
import kotlin.streams.toList

fun main() {
    fun part1(lanternFishes: MutableList<LanternFish>, days: Int): Long {
        var newLanternFishes: MutableList<LanternFish> = mutableListOf()
        repeat(days) {
            lanternFishes.forEach { fish ->
                fish.newDay().ifPresent { newFish -> newLanternFishes.add(newFish) }
            }
            lanternFishes.addAll(newLanternFishes)
            newLanternFishes = mutableListOf()
        }
        return lanternFishes.size.toLong()
    }

    fun part2(input: Array<Long>, days: Int): Long {
        var fishes = input

        repeat(days) {
            val newFishes = Array(9) { 0L }
            for (i in 0..8) {
                if (i == 0) {
                    newFishes[6] += fishes[i]
                    newFishes[8] += fishes[i]
                } else {
                    newFishes[i - 1] += fishes[i]
                }
            }
            fishes = newFishes
        }

        return fishes.sum()
    }

    val testInput = readInput("Day06/Day06_test")[0].split(",").stream().map { it.toInt() }.toList()
    val testInputPart1 = testInput.map { LanternFish(it) }.toList()
    val testInputPart2 = Array(9) { 0L }
    testInput.forEach {
        testInputPart2[it]++
    }

    check(part1(testInputPart1.toMutableList(), 80) == 5934L)
    check(part2(testInputPart2, 256) == 26984457539)

    val input = readInput("Day06/Day06")[0].split(",").stream().map { it.toInt() }.toList()
    val inputPart2 = Array(9) { 0L }
    val inputPart1 = input.map { LanternFish(it) }.toList().toMutableList()
    input.forEach {
        inputPart2[it]++
    }

    println(part1(inputPart1, 80))
    println(part2(inputPart2, 256))
}
