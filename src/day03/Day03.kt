package day03

import println
import readInput


fun main() {

    val day = "day03"

    fun part1(input: List<String>): Int {
        val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
        val sum = regex.findAll(input.joinToString())
            .map { matchResult ->
                val (a, b) = matchResult.destructured
                a.toInt() * b.toInt()
            }
            .sum()
        return sum
    }

    fun part2(input: List<String>): Int {
        val regex = Regex("""(do\(\)|don't\(\)|mul\((\d{1,3}),(\d{1,3})\))""")
        var enabled = true
        var sum = 0

        regex.findAll(input.joinToString()).forEach { matchResult ->
            println(matchResult)
            when {
                matchResult.value == "do()" -> enabled = true
                matchResult.value == "don't()" -> enabled = false
                matchResult.value.startsWith("mul(") && enabled -> {
                    val (a, b) = matchResult.groupValues.drop(2)
                    sum += a.toInt() * b.toInt()
                }
            }
        }
        return sum
    }

    val input = readInput("${day}/Day03")
    val testInput = readInput("${day}/Day03_test")

// PART 01
    check(part1(testInput) == 161)
    part1(input).println()

// PART 02    
    check(part2(testInput) == 48)
    part2(input).println()
}
