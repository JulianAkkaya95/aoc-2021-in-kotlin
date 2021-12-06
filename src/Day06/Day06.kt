package Day06

import Day06.Domain.LanternFish
import readInput
import kotlin.streams.toList

fun main() {
    fun part1(lanternFishes: MutableList<LanternFish>, days: Int): Long {
        var newLanternFishes: MutableList<LanternFish> = mutableListOf()
        for (i in 1..days) {
            lanternFishes.forEach{ fish ->
                fish.newDay().ifPresent { newFish -> newLanternFishes.add(newFish) }
            }
            lanternFishes.addAll(newLanternFishes)
            newLanternFishes = mutableListOf()
        }
        return lanternFishes.size.toLong()
    }


    val testInput = readInput("Day06/Day06_test")[0].split(",").stream().map { it.toInt() }.map { LanternFish(it) }.toList().toMutableList()
    check(part1(testInput.toMutableList(), 80) == 5934L)
    //check(part1(testInput.toMutableList(), 256) == 26984457539)


    //val input = readInput("Day06/Day06")[0].split(",").stream().map { it.toInt() }.map { LanternFish(it) }.toList().toMutableList()
    //println(part1(input.toMutableList(), 80))
    //println(part1(input.toMutableList(), 256))
}
