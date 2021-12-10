package day04.domain

import kotlin.streams.toList

class BingoGrid(val gridNumbers: List<MutableList<Int>>) {

    /*public fun markNumber(number: Int): String? {
        gridNumbers.forEach { column ->
            column.removeIf { it.equals(number) }
            if(column.isEmpty()) return "Bingo"
        }
        return null
    }

    public fun calculateResult(): Int {
        return gridNumbers.stream().map {
            it.sum()
        }.toList().sum()
    }*/
}