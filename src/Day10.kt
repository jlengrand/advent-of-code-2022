fun main() {
    fun part1(input: List<String>): Int {

        var x = 1
        var cycle = 0
        val strenghts = mutableListOf<Int>()

        input.forEach {
            if(it == "noop"){
                cycle += 1
                if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220 ){
                    strenghts.add(x * cycle)
                }
            }
            else{
                val toAdd = it.removePrefix("addx ").toInt()
                for(i in 1..2){
                    cycle += 1
                    if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220 ){
                        strenghts.add(x * cycle)
                    }
                }
                x += toAdd
            }
        }

        return strenghts.sum()
    }

    fun part2(input: List<String>) {
        val screen = MutableList(240) { "0" }

        var x = 1
        var cycle = 0

        input.forEach {
            if(it == "noop"){
                cycle += 1


                // Draw
                if( (cycle -1) % 40 == x-1 || (cycle -1) % 40   == x || (cycle -1)%40  == x+1 ){
                    screen[cycle - 1] = "#"
                }
                else{
                    screen[cycle - 1] = "."
                }
            }
            else{
                val toAdd = it.removePrefix("addx ").toInt()
                for(i in 1..2){
                    cycle += 1

                    // Draw
                    if( (cycle -1) % 40 == x-1 || (cycle -1) % 40   == x || (cycle -1)%40  == x+1 ){
                        screen[cycle - 1] = "#"
                    }
                    else{
                        screen[cycle - 1] = "."
                    }
                }
                x += toAdd
            }
        }

        println(screen.joinToString("").chunked(40))
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test2")
    check(part1(testInput) == 13140)
//    check(part2(testInput) == 5)
    part2(testInput)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}