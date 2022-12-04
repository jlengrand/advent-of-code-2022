fun main() {
    fun part1(input: List<String>): Int {
        var contained = 0
        for (line in input){
            val elves = line.split(",")
            val rangesAsList = elves.map { it.split("-") }
            val ranges = rangesAsList.map { (it[0].toInt()..it[1].toInt()).toList()   }

            if(ranges[0].containsAll(ranges[1]) || ranges[1].containsAll(ranges[0])){
                contained += 1
            }
        }

        return contained
    }

    fun part2(input: List<String>): Int {
        var overlap = 0
        for (line in input){
            val elves = line.split(",")
            val rangesAsList = elves.map { it.split("-") }
            val ranges = rangesAsList.map { (it[0].toInt()..it[1].toInt()).toList()   }

            if(ranges[0].any{ranges[1].contains(it)} || ranges[1].any{ranges[0].contains(it)} ){
                overlap += 1
            }
        }

        return overlap
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}