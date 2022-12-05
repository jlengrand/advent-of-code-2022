
val realStacks =
    hashMapOf(
        1 to mutableListOf("R", "W", "F", "H", "T", "S"),
        2 to mutableListOf("W", "Q", "D", "G", "S"),
        3 to mutableListOf("W", "T", "B"),
        4 to mutableListOf("J", "Z", "Q", "N", "T", "W", "R", "D"),
        5 to mutableListOf("Z", "T", "V", "L", "G", "H", "B", "F"),
        6 to mutableListOf("G", "S", "B", "V", "C", "T", "P", "L"),
        7 to mutableListOf("P", "G", "W", "T", "R", "B", "Z"),
        8 to mutableListOf("R", "J", "C", "T", "M", "G", "N"),
        9 to mutableListOf("W", "B", "G", "L")
    )

val exampleStacks =
    hashMapOf(
        1 to mutableListOf("N", "Z"),
        2 to mutableListOf("D", "C", "M"),
        3 to mutableListOf("P"),
    )
fun main() {

    data class Move(val number: Int, val from: Int, val to: Int)

    fun getMove(input: String): Move {

        val numberAndGibber = input.split("move")[1]
            .split("from")

        val fromAndGibber = numberAndGibber[1].split("to")

        return Move(
            numberAndGibber[0].filter { !it.isWhitespace() }.toInt(),
            fromAndGibber[0].filter { !it.isWhitespace() }.toInt(),
            fromAndGibber[1].filter { !it.isWhitespace() }.toInt())
    }

    fun applyMove(stacks: HashMap<Int, MutableList<String>>, move: Move): HashMap<Int, MutableList<String>> {

        for(i in 1..move.number){
            val item = stacks[move.from]!!.take(1).first()
            val newStackList = stacks[move.from]!!.drop(1)
            stacks[move.from] = newStackList.toMutableList()
            stacks[move.to]!!.add(0, item)
        }

        return stacks
    }

    fun applyMove9001(stacks: HashMap<Int, MutableList<String>>, move: Move): HashMap<Int, MutableList<String>> {

            val items = stacks[move.from]!!.take(move.number)
            val newStackList = stacks[move.from]!!.drop(move.number)
            stacks[move.from] = newStackList.toMutableList()
            stacks[move.to]!!.addAll(0,items)

        return stacks
    }

    fun getTopstacks(stacks: HashMap<Int, MutableList<String>>): String {

        return stacks.map { it.value.first() }.joinToString("")
    }

    fun part1(input: List<String>, drop: Int = 5, stacks: HashMap<Int, MutableList<String>> = exampleStacks): String {

        var inStacks = stacks

        val cut = input.drop(drop)

        for(line in cut){
            val move = getMove(line)
            inStacks = applyMove(inStacks, move)
        }

        return getTopstacks(inStacks)
    }

    fun part2(input: List<String>, drop: Int = 5, stacks: HashMap<Int, MutableList<String>> = exampleStacks): String {
        var inStacks = stacks

        val cut = input.drop(drop)

        for(line in cut){
            val move = getMove(line)
            inStacks = applyMove9001(inStacks, move)
        }

        return getTopstacks(inStacks)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input, 10, realStacks))
    println(part2(input, 10, realStacks))
}