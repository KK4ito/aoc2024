package day01

import println
import readInput

fun main() {
    
    val day = "day01"

    fun part1(input: List<String>): Int {
        val (sortedLeft, sortedRight) = input.map { it.split("   ") }
            .map { it[0] to it[1] }
            .unzip()
            .let { (left, right) ->
                left.map(String::toInt).sorted() to right.map(String::toInt).sorted()
            }
        val totalDistance = sortedLeft.zip(sortedRight) { a, b -> Math.abs(a - b) }
            .sum()
        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val (leftList, rightList) = input.map { it.split("   ") }
            .map { it[0] to it[1] }
            .unzip()
            .let { (left, right) ->
                left.map(String::toInt) to right.map(String::toInt)
            }
        val rightCounts = rightList.groupingBy { it }.eachCount()
        val similarityScore = leftList.sumOf { num ->
            num * (rightCounts[num] ?: 0)
        }
        return similarityScore
    }
    
    val input = readInput("${day}/Day01")
    val testInput = readInput("${day}/Day01_test")

// PART 01
    check(part1(testInput) == 11)
    part1(input).println()

// PART 02    
    check(part2(testInput) == 31)
    part2(input).println()
}
