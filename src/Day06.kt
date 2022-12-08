fun main() {
    fun part1(input: List<String>): Int {
        for(i in 0..input[0].length-4){
            val window = input[0].slice(i..i+3)
            if (window.toList().toSet().size == window.length){
                return i + 4
            }
        }

        return 0
    }

    fun part3(input: String, length: Int): Int {

        return input.toList()
            .windowed(size = length, step = 1)
            .indexOfFirst { it.toSet().size == length }
    }

    fun part2(input: List<String>): Int {
        for(i in 0..input[0].length-14){
            val window = input[0].slice(i..i+13)
            if (window.toList().toSet().size == window.length){
                return i + 14
            }
        }

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}