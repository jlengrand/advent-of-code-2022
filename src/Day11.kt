import java.math.BigInteger

fun main() {

    data class Monkey(
        val name : Int,
        var items:  MutableList<BigInteger>,
        val operation : (BigInteger) -> BigInteger,
        val dividible : BigInteger,
        val trueThrow : Int,
        val falseThrow: Int,
        var inspected : BigInteger = BigInteger.ZERO
    ){
        override fun toString(): String {
            return "Monkey(name=$name, inspected=${inspected}, items=$items)"
        }
    }
    val testMonkeys1 = arrayOf<Monkey> (
        Monkey(0,
            mutableListOf(BigInteger.valueOf(79L), BigInteger.valueOf(98L)),
            {old -> old * BigInteger.valueOf(19)},
            BigInteger.valueOf(23),
            2,
            3
        ),

        Monkey(1,
            mutableListOf(BigInteger.valueOf(54), BigInteger.valueOf(65), BigInteger.valueOf(75), BigInteger.valueOf(74)),
            {old -> old + BigInteger.valueOf(6)},
            BigInteger.valueOf(19),
            2,
            0
        ),

        Monkey(2,
            mutableListOf(BigInteger.valueOf(79), BigInteger.valueOf(60), BigInteger.valueOf(97)),
            {old -> old* old},
            BigInteger.valueOf(13),
            1,
            3
        ),

        Monkey(3,
            mutableListOf(BigInteger.valueOf(74)),
            {old -> old + BigInteger.valueOf(3)},
            BigInteger.valueOf(17),
            0,
            1
        )

    )

    val testMonkeys2 = arrayOf<Monkey> (
        Monkey(0,
            mutableListOf(BigInteger.valueOf(79L), BigInteger.valueOf(98L)),
            {old -> old * BigInteger.valueOf(19)},
            BigInteger.valueOf(23),
            2,
            3
        ),

        Monkey(1,
            mutableListOf(BigInteger.valueOf(54), BigInteger.valueOf(65), BigInteger.valueOf(75), BigInteger.valueOf(74)),
            {old -> old + BigInteger.valueOf(6)},
            BigInteger.valueOf(19),
            2,
            0
        ),

        Monkey(2,
            mutableListOf(BigInteger.valueOf(79), BigInteger.valueOf(60), BigInteger.valueOf(97)),
            {old -> old* old},
            BigInteger.valueOf(13),
            1,
            3
        ),

        Monkey(3,
            mutableListOf(BigInteger.valueOf(74)),
            {old -> old + BigInteger.valueOf(3)},
            BigInteger.valueOf(17),
            0,
            1
        )

    )

    val realMonkeys2 = arrayOf<Monkey> (
        Monkey(0,
            mutableListOf(BigInteger.valueOf(56), BigInteger.valueOf(52), BigInteger.valueOf(58), BigInteger.valueOf(96), BigInteger.valueOf(70), BigInteger.valueOf(75), BigInteger.valueOf(72)),
            {old -> old * BigInteger.valueOf(17)},
            BigInteger.valueOf(11),
            2,
            3
        ),

        Monkey(1,
            mutableListOf(BigInteger.valueOf(75), BigInteger.valueOf(58), BigInteger.valueOf(86), BigInteger.valueOf(80), BigInteger.valueOf(55), BigInteger.valueOf(81)),
            {old -> old + BigInteger.valueOf(7)},
            BigInteger.valueOf(3),
            6,
            5
        ),

        Monkey(2,
            mutableListOf(BigInteger.valueOf(73), BigInteger.valueOf(68), BigInteger.valueOf(73), BigInteger.valueOf(90)),
            {old -> old* old},
            BigInteger.valueOf(5),
            1,
            7
        ),

        Monkey(3,
            mutableListOf(BigInteger.valueOf(72), BigInteger.valueOf(89),BigInteger.valueOf( 55),BigInteger.valueOf( 51), BigInteger.valueOf(59)),
            {old -> old + BigInteger.valueOf(1)},
            BigInteger.valueOf(7),
            2,
            7
        ),

        Monkey(4,
            mutableListOf(BigInteger.valueOf(76), BigInteger.valueOf(76), BigInteger.valueOf(91)),
            {old -> old * BigInteger.valueOf(3)},
            BigInteger.valueOf(19),
            0,
            3
        ),

        Monkey(5,
            mutableListOf(BigInteger.valueOf(88)),
            {old -> old + BigInteger.valueOf(4)},
            BigInteger.valueOf(2),
            6,
            4
        ),

        Monkey(6,
            mutableListOf(BigInteger.valueOf(64), BigInteger.valueOf(63), BigInteger.valueOf(56), BigInteger.valueOf(50), BigInteger.valueOf(77), BigInteger.valueOf(55), BigInteger.valueOf(55), BigInteger.valueOf(86)),
            {old -> old + BigInteger.valueOf(8)},
            BigInteger.valueOf(13),
            4,
            0
        ),

        Monkey(7,
            mutableListOf(BigInteger.valueOf(79), BigInteger.valueOf(58)),
            {old -> old + BigInteger.valueOf(6)},
            BigInteger.valueOf(17),
            1,
            5
        ),

        )

    val realMonkeys = arrayOf<Monkey> (
        Monkey(0,
            mutableListOf(BigInteger.valueOf(56), BigInteger.valueOf(52), BigInteger.valueOf(58), BigInteger.valueOf(96), BigInteger.valueOf(70), BigInteger.valueOf(75), BigInteger.valueOf(72)),
            {old -> old * BigInteger.valueOf(17)},
            BigInteger.valueOf(11),
            2,
            3
        ),

        Monkey(1,
            mutableListOf(BigInteger.valueOf(75), BigInteger.valueOf(58), BigInteger.valueOf(86), BigInteger.valueOf(80), BigInteger.valueOf(55), BigInteger.valueOf(81)),
            {old -> old + BigInteger.valueOf(7)},
            BigInteger.valueOf(3),
            6,
            5
        ),

        Monkey(2,
            mutableListOf(BigInteger.valueOf(73), BigInteger.valueOf(68), BigInteger.valueOf(73), BigInteger.valueOf(90)),
            {old -> old* old},
            BigInteger.valueOf(5),
            1,
            7
        ),

        Monkey(3,
            mutableListOf(BigInteger.valueOf(72), BigInteger.valueOf(89),BigInteger.valueOf( 55),BigInteger.valueOf( 51), BigInteger.valueOf(59)),
            {old -> old + BigInteger.valueOf(1)},
            BigInteger.valueOf(7),
            2,
            7
        ),

        Monkey(4,
            mutableListOf(BigInteger.valueOf(76), BigInteger.valueOf(76), BigInteger.valueOf(91)),
            {old -> old * BigInteger.valueOf(3)},
            BigInteger.valueOf(19),
            0,
            3
        ),

        Monkey(5,
            mutableListOf(BigInteger.valueOf(88)),
            {old -> old + BigInteger.valueOf(4)},
            BigInteger.valueOf(2),
            6,
            4
        ),

        Monkey(6,
            mutableListOf(BigInteger.valueOf(64), BigInteger.valueOf(63), BigInteger.valueOf(56), BigInteger.valueOf(50), BigInteger.valueOf(77), BigInteger.valueOf(55), BigInteger.valueOf(55), BigInteger.valueOf(86)),
            {old -> old + BigInteger.valueOf(8)},
            BigInteger.valueOf(13),
            4,
            0
        ),

        Monkey(7,
            mutableListOf(BigInteger.valueOf(79), BigInteger.valueOf(58)),
            {old -> old + BigInteger.valueOf(6)},
            BigInteger.valueOf(17),
            1,
            5
        ),

    )

    fun part1(input: List<String>, monkeys: Array<Monkey> = testMonkeys1): BigInteger {

        for(i in 1..20){
            monkeys.forEach {monkey ->
                monkey.items.forEach{ item ->
                    monkey.inspected += BigInteger.ONE

                    var newVal = monkey.operation(item)
                    newVal /= BigInteger.valueOf(3)

                    if(newVal % monkey.dividible == BigInteger.ZERO){
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

    fun part2(input: List<String>, monkeys: Array<Monkey> = testMonkeys2): BigInteger {

        for(i in 1..10000){
            if(i % 200 == 0){
                println(i)
            }

            monkeys.forEach {monkey ->
                monkey.items.forEach{ item ->
                    monkey.inspected += BigInteger.ONE

                    var newVal = monkey.operation(item)
                    newVal %= BigInteger.valueOf(9699690)

                    if(newVal % monkey.dividible == BigInteger.ZERO){
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
    check(part1(testInput, testMonkeys1) == BigInteger.valueOf(10605))
    check(part2(testInput, testMonkeys2) == BigInteger.valueOf(2713310158))

    val input = readInput("Day11")
    println(part1(input, realMonkeys))
    println(part2(input, realMonkeys2))
}