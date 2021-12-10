package day10

import readInput

fun main() {
    val openAndCloseChars: Map<Char, Char> = mapOf(Pair(')', '('), Pair(']', '['), Pair('}', '{'), Pair('>', '<'))

    fun part2(openChunk: MutableList<Char>): Long {
        val autocompletePoints: Map<Char, Long> = mapOf(Pair(')', 1L), Pair(']', 2L), Pair('}', 3L), Pair('>', 4L))
        var autocompleteScore = 0L

        openChunk.reverse()
        openChunk.forEach{ char ->
            val closingChar = openAndCloseChars.filterValues { it == char }.keys.first()
            autocompleteScore = autocompleteScore * 5 + autocompletePoints[closingChar]!!
        }
        
        return autocompleteScore
    }

    fun part1(input: List<String>): Pair<Long, Long> {
        var syntaxErrorScore = 0L
        val allAutocompleteScores = mutableListOf<Long>()
        val syntaxErrorPoints: Map<Char, Long> = mapOf(Pair(')', 3L), Pair(']', 57L), Pair('}', 1197), Pair('>', 25137))

         input.forEach outer@ { chunk ->
            val openChunks: MutableList<Char> = mutableListOf()
            chunk.forEach { char ->
                if (openAndCloseChars.containsValue(char)) {
                    openChunks.add(char)
                } else {
                    if (openAndCloseChars[char] != openChunks.last()) {
                        syntaxErrorScore += syntaxErrorPoints[char]!!
                        openChunks.removeAll { true }
                        return@outer
                    }
                    openChunks.removeLast()
                }
            }
            allAutocompleteScores.add(part2(openChunks))
        }

        allAutocompleteScores.sort()
        return Pair(syntaxErrorScore, allAutocompleteScores[allAutocompleteScores.size / 2])
    }

    val testSolutions: Pair<Long, Long> = part1(readInput("day10/Day10_test"))
    check(testSolutions.first == 26397L)
    check(testSolutions.second == 288957L)

    val solutions: Pair<Long, Long> = part1(readInput("day10/Day10"))
    println(solutions.first)
    println(solutions.second)
}