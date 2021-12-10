package day05

import readInput

fun main() {

    fun convertInput(input: List<String>): MutableList<Pair<Pair<Long, Long>, Pair<Long, Long>>> {
        val coordinates: MutableList<Pair<Pair<Long, Long>, Pair<Long, Long>>> = mutableListOf()

        input.stream().forEach {
            val streamCoordinatesAsStrings: List<String> = it.split(" -> ")
            val startCoordinateAsLong: Pair<Long, Long>
            val endCoordinateAsLong: Pair<Long, Long>
            streamCoordinatesAsStrings[0].split(",").let {
                startCoordinateAsLong = Pair(it[0].toLong(), it[1].toLong())
            }
            streamCoordinatesAsStrings[1].split(",").let {
                endCoordinateAsLong = Pair(it[0].toLong(), it[1].toLong())
            }

            coordinates.add(Pair(startCoordinateAsLong, endCoordinateAsLong))
        }

        return coordinates
    }

    fun removeNotRelevantData(coordinates: MutableList<Pair<Pair<Long, Long>, Pair<Long, Long>>>): MutableList<Pair<Pair<Long, Long>, Pair<Long, Long>>> {
        return coordinates.filter {
            val startCoordinate: Pair<Long, Long> = it.first
            val endCoordinate: Pair<Long, Long> = it.second

            (startCoordinate.first == endCoordinate.first || startCoordinate.second == endCoordinate.second)
        }.toMutableList()
    }


    fun createMap(coordinates: MutableList<Pair<Pair<Long, Long>, Pair<Long, Long>>>): MutableList<MutableList<Long>> {
        val xCoordinates = mutableListOf<Long>()
        val yCoordinates = mutableListOf<Long>()
        coordinates.stream().forEach {
            xCoordinates.add(it.first.first)
            xCoordinates.add(it.second.first)
            yCoordinates.add(it.first.second)
            yCoordinates.add(it.second.second)
        }

        val highestXCoordinate = xCoordinates.maxOf { it }
        val highestYCoordinate = yCoordinates.maxOf { it }

        val map: MutableList<MutableList<Long>> = mutableListOf()

        for (i in 0..highestXCoordinate) {
            val yList = mutableListOf<Long>()
            for (j in 0..highestYCoordinate) {
                yList.add(0)
            }
            map.add(yList)
        }

        return map
    }

    fun part1(input: List<String>): Long {
        val coordinates: MutableList<Pair<Pair<Long, Long>, Pair<Long, Long>>> =
            removeNotRelevantData(convertInput(input))
        val map: MutableList<MutableList<Long>> = createMap(coordinates)

        coordinates.forEach {
            val startX: Long
            val startY: Long
            val endX: Long
            val endY: Long

            if (it.first.first < it.second.first) {
                startX = it.first.first
                endX = it.second.first
            } else {
                startX = it.second.first
                endX = it.first.first
            }

            if (it.first.second < it.second.second) {
                startY = it.first.second
                endY = it.second.second
            } else {
                startY = it.second.second
                endY = it.first.second
            }

            if (startX != endX) {
                for (i in startX..endX) {
                    map[i.toInt()][startY.toInt()]++
                }
            }

            if (startY != endY) {
                for (i in startY..endY) {
                    map[startX.toInt()][i.toInt()]++
                }
            }
        }

        var resultCounter = 0L

        map.forEach {
            it.forEach {
                if (it > 1) resultCounter++
            }
        }

        return resultCounter
    }

    fun part2(input: List<String>): Long {
        val coordinates: MutableList<Pair<Pair<Long, Long>, Pair<Long, Long>>> =
            convertInput(input)
        val map: MutableList<MutableList<Long>> = createMap(coordinates)

        coordinates.forEach {
            var startX: Long
            var startY: Long
            val endX: Long
            val endY: Long

            if (it.first.first < it.second.first) {
                startX = it.first.first
                endX = it.second.first
            } else {
                startX = it.second.first
                endX = it.first.first
            }

            if (it.first.second < it.second.second) {
                startY = it.first.second
                endY = it.second.second
            } else {
                startY = it.second.second
                endY = it.first.second
            }

            while (startX <= endX) {
                map[startX.toInt()][startY.toInt()]++
                startX++
                if (startY != endY) {
                    startY++
                }
            }
        }

        map.forEach{
            println(it)
        }


        return 0L
    }


    val testInput = readInput("day05/Day05_test")
    //check(part1(testInput) == 5L)
    check(part2(testInput) == 12L)


    val input = readInput("day05/Day05")
    println(part1(input))
}
