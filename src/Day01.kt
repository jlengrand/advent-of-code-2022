fun main() {
    fun part1(input: List<String>): Long {

        val elfItems : MutableList<List<Long>> = mutableListOf()

        var item = mutableListOf<Long>()
        for (i in input){
            if(i.isEmpty()){
                elfItems.add(item)
                item = mutableListOf<Long>()
            }
            else{
                item.add(i.toLong())
            }
        }

        return elfItems.maxOfOrNull { it.sum() } ?:0
    }

    fun part2(input: List<String>): Long {
        val elfItems : MutableList<List<Long>> = mutableListOf()

        var item = mutableListOf<Long>()
        for (i in input){
            if(i.isEmpty()){
                elfItems.add(item)
                item = mutableListOf<Long>()
            }
            else{
                item.add(i.toLong())
            }
        }
        elfItems.add(item)

        val orderedSums = elfItems.map { it.sum() }.sortedDescending().take(3)

        return orderedSums.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000.toLong())
    check(part2(testInput) == 45000.toLong())

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}