fun main() {

    fun toPriority(c: Char): Int {
        return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(c) + 1
    }

    fun part1(input: List<String>): Int {

        val items = input.map { s -> Pair(s.substring(0, s.length /2), s.substring(s.length /2 )) }
        val commonItems = items.map{ it.first.asIterable().intersect(it.second.asIterable()).first() }
        val priorities = commonItems.map { toPriority(it) }
        return priorities.sum()
    }

    fun part2(input: List<String>): Int {
        val items = input.chunked(3)
        val commonItems = items.map{ it[0].asIterable().intersect(it[1].asIterable()).toList() }
        val commonItems2 = items.mapIndexed{ idx, l -> l[2].asIterable().intersect(commonItems[idx]).first() }

        val priorities = commonItems2.map { toPriority(it) }

        return priorities.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}