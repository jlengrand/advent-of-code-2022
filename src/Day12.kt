fun main() {
    fun part1(input: List<String>): Int {

        val width = input.first().length
        val height = input.size

        val grid = Array(height) { CharArray(width)}
        val visibleGrid = Array(height) { CharArray(width)}

        input.forEachIndexed { idx, line ->
            line.forEachIndexed { index, char ->
                grid[idx][index] = char
            }
        }


        var startIdx = Pair(0, 0)
        var endIdx = Pair(0, 0)
        grid.forEachIndexed{yIdx, row ->
            row.forEachIndexed { xIdx, c ->
                if (c == 'S'.toChar()){
                    startIdx = Pair(xIdx, yIdx)
                }
                if (c == 'E'.toChar()){
                    endIdx = Pair(xIdx, yIdx)
                }
            }
        }

        val paths = mutableListOf<List<Pair<Int, Int>>>()

        println("a".toCharArray().first().code)

        println(startIdx)
        println(endIdx)


        val res = input.map { line ->
            line.toCharArray().map { it.code - 97 }
        }

        val res2 = res.map{ it.joinToString("")}

        println(res2)

        TODO()
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
//    check(part1(testInput) == 31)
//    check(part2(testInput) == 5)

    val input = readInput("Day12")
    println(part1(input))
//    println(part2(input))
}