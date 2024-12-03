package day02

import println
import readInput
import kotlin.math.absoluteValue


fun main() {

    val day = "day02"

    fun part1(input: List<String>): Int {
        val result = input.map { it.split(" ") }
            .map { it.map(String::toInt) }
            .filter { levels ->
                val differences = levels.zipWithNext().map { (a, b) -> b - a }
                (differences.all { it > 0 } || differences.all { it < 0 }) && differences.all { it.absoluteValue in 1..3 }
            }
        return result.size
    }

    fun part2(input: List<String>): Int {
        val result = input.map { it.split(" ").map(String::toInt) }
            .count { sequence ->
                sequence.zipWithNext().let { pairs ->
                    (pairs.all { (a, b) -> b - a in 1..3 } || pairs.all { (a, b) -> b - a in -3..-1 }) ||
                            sequence.indices.any { i ->
                                sequence.filterIndexed { index, _ -> index != i }
                                    .zipWithNext().let { modifiedPairs ->
                                        (modifiedPairs.all { (a, b) -> b - a in 1..3 } || modifiedPairs.all { (a, b) -> b - a in -3..-1 })
                                    }
                            }
                }
            }

        return result
    }

    val input = readInput("${day}/Day02")
    val testInput = readInput("${day}/Day02_test")

// PART 01
    check(part1(testInput) == 2)
    part1(input).println()

// PART 02    
    check(part2(testInput) == 4)
    part2(input).println()
}
