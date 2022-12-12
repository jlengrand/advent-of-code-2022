import java.math.BigInteger

fun main() {

    data class Monkey(
        val name : Int,
        var items:  MutableList<Long>,
        val operation : (Long) -> Long,
        val dividible : Long,
        val trueThrow : Int,
        val falseThrow: Int,
        var inspected : Long = 0L
    ){
        override fun toString(): String {
            return "Monkey(name=$name, inspected=${inspected}, items=$items)"
        }
    }

    val testMonkeys2 = arrayOf<Monkey> (
        Monkey(0,
            mutableListOf(79L, 98L),
            {old -> old * 19},
            23,
            2,
            3
        ),

        Monkey(1,
            mutableListOf(54, 65, 75, 74),
            {old -> old + 6},
            19,
            2,
            0
        ),

        Monkey(2,
            mutableListOf(79, 60, 97),
            {old -> old* old},
            13,
            1,
            3
        ),

        Monkey(3,
            mutableListOf(74),
            {old -> old + 3},
            17,
            0,
            1
        )

    )


    val testMonkeys1 = arrayOf<Monkey> (
        Monkey(0,
            mutableListOf(79L, 98L),
            {old -> old * 19},
            23,
            2,
            3
        ),

        Monkey(1,
            mutableListOf(54, 65, 75, 74),
            {old -> old + 6},
            19,
            2,
            0
        ),

        Monkey(2,
            mutableListOf(79, 60, 97),
            {old -> old* old},
            13,
            1,
            3
        ),

        Monkey(3,
            mutableListOf(74),
            {old -> old + 3},
            17,
            0,
            1
        )

    )

    val realMonkeys = arrayOf<Monkey> (
        Monkey(0,
            mutableListOf(56, 52, 58, 96, 70, 75, 72),
            {old -> old * 17},
            11,
            2,
            3
        ),

        Monkey(1,
            mutableListOf(75, 58, 86, 80, 55, 81),
            {old -> old + 7},
            3,
            6,
            5
        ),

        Monkey(2,
            mutableListOf(73, 68, 73, 90),
            {old -> old* old},
            5,
            1,
            7
        ),

        Monkey(3,
            mutableListOf(72, 89, 55, 51, 59),
            {old -> old + 1},
            7,
            2,
            7
        ),

        Monkey(4,
            mutableListOf(76, 76, 91),
            {old -> old * 3},
            19,
            0,
            3
        ),

        Monkey(5,
            mutableListOf(88),
            {old -> old + 4},
            2,
            6,
            4
        ),

        Monkey(6,
            mutableListOf(64, 63, 56, 50, 77, 55, 55, 86),
            {old -> old + 8},
            13,
            4,
            0
        ),

        Monkey(7,
            mutableListOf(79, 58),
            {old -> old + 6},
            17,
            1,
            5
        ),

    )

    val realMonkeys2 = arrayOf<Monkey> (
        Monkey(0,
            mutableListOf(56, 52, 58, 96, 70, 75, 72),
            {old -> old * 17},
            11,
            2,
            3
        ),

        Monkey(1,
            mutableListOf(75, 58, 86, 80, 55, 81),
            {old -> old + 7},
            3,
            6,
            5
        ),

        Monkey(2,
            mutableListOf(73, 68, 73, 90),
            {old -> old* old},
            5,
            1,
            7
        ),

        Monkey(3,
            mutableListOf(72, 89, 55, 51, 59),
            {old -> old + 1},
            7,
            2,
            7
        ),

        Monkey(4,
            mutableListOf(76, 76, 91),
            {old -> old * 3},
            19,
            0,
            3
        ),

        Monkey(5,
            mutableListOf(88),
            {old -> old + 4},
            2,
            6,
            4
        ),

        Monkey(6,
            mutableListOf(64, 63, 56, 50, 77, 55, 55, 86),
            {old -> old + 8},
            13,
            4,
            0
        ),

        Monkey(7,
            mutableListOf(79, 58),
            {old -> old + 6},
            17,
            1,
            5
        ),

        )

    fun part1(input: List<String>, monkeys: Array<Monkey> = testMonkeys1): Long {

        for(i in 1..20){
            monkeys.forEach {monkey ->
                monkey.items.forEach{ item ->
                    monkey.inspected += 1

                    var newVal = monkey.operation(item)
                    newVal /= 3

                    if(newVal % monkey.dividible == 0L){
                        monkeys[monkey.trueThrow].items.add(newVal)
                    }
                    else{
                        monkeys[monkey.falseThrow].items.add(newVal)
                    }
                }
                monkey.items = mutableListOf()
            }
        }

        val sortedMonkeys = monkeys.sortedBy { it.inspected }.reversed().take(2)
        return sortedMonkeys[0].inspected * sortedMonkeys[1].inspected
    }

    fun part2(input: List<String>, monkeys: Array<Monkey> = testMonkeys2): Long {
        val denominator = monkeys.map { it.dividible }.fold(1L) { acc, d -> d * acc } // All monkey dividibles multiplied

        for(i in 1..10000){
            if(i % 200 == 0){
                println(i)
            }

            monkeys.forEach {monkey ->
                monkey.items.forEach{ item ->
                    monkey.inspected += 1

                    var newVal = monkey.operation(item)
                    if(newVal >= denominator) newVal %= denominator

                    if(newVal % monkey.dividible == 0L){
                        monkeys[monkey.trueThrow].items.add(newVal)
                    }
                    else{
                        monkeys[monkey.falseThrow].items.add(newVal)
                    }
                }
                monkey.items = mutableListOf()
            }
        }

        val sortedMonkeys = monkeys.sortedBy { it.inspected }.reversed().take(2)
        return sortedMonkeys[0].inspected * sortedMonkeys[1].inspected
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput, testMonkeys1) == 10605L)
//    check(part2(testInput, testMonkeys2) == 2713310158L)

    val input = readInput("Day11")
    println(part1(input, realMonkeys))
    println(part2(input, realMonkeys2))
}