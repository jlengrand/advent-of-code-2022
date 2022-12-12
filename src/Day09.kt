import kotlin.system.exitProcess

fun main() {
    data class Pos(val x : Int = 0, val y : Int = 0)

    fun part1(input: List<String>): Int {
        val tailPositions = mutableListOf<Pos>()

        var tail = Pos()
        var head = Pos()

        for(line in input){
            val (side: String, num: String) = line.split(" ")

            for(iteration in 1..num.toInt()){

                head = when(side){
                    "R" -> Pos(head.x + 1, head.y)
                    "L" -> Pos(head.x - 1, head.y)
                    "U" -> Pos(head.x, head.y + 1)
                    "D" -> Pos(head.x, head.y - 1)
                    else -> exitProcess(0) // Very fishy
                }

                val relPos = Pos(head.x - tail.x, head.y - tail.y)
                tail = if(relPos.x == 2 && relPos.y == 0){ Pos(tail.x + 1, tail.y) }
                        else if(relPos.x == -2 && relPos.y == 0) { Pos(tail.x - 1, tail.y) }
                        else if(relPos.x == 0 && relPos.y == 2) { Pos(tail.x, tail.y + 1) }
                        else if(relPos.x == 0 && relPos.y == -2) { Pos(tail.x, tail.y - 1) }

                        else if(relPos.x == 2 && relPos.y == 1) { Pos(tail.x + 1, tail.y + 1) }
                        else if(relPos.x == 2 && relPos.y == -1) { Pos(tail.x + 1, tail.y - 1) }

                        else if(relPos.x == -1 && relPos.y == 2) { Pos(tail.x - 1, tail.y + 1) }
                        else if(relPos.x == 1 && relPos.y == 2) { Pos(tail.x + 1, tail.y + 1) }


                        else if(relPos.x == -2 && relPos.y == 1) { Pos(tail.x - 1, tail.y + 1) }
                        else if(relPos.x == -2 && relPos.y == -1) { Pos(tail.x - 1, tail.y - 1) }

                        else if(relPos.x == -1 && relPos.y == -2) { Pos(tail.x - 1, tail.y - 1) }
                        else if(relPos.x ==  1 && relPos.y == -2) { Pos(tail.x + 1, tail.y - 1) }

                        else{ tail }

                tailPositions.add(tail)
            }

        }

        return tailPositions.toSet().size
    }

    fun part2(input: List<String>): Int {
        val tailPositions = mutableListOf<Pos>()

        val knots = MutableList(10) { Pos() }
        var head = Pos()

        for(line in input){
            val (side: String, num: String) = line.split(" ")

            for(iteration in 1..num.toInt()){

                head = when(side){
                    "R" -> Pos(head.x + 1, head.y)
                    "L" -> Pos(head.x - 1, head.y)
                    "U" -> Pos(head.x, head.y + 1)
                    "D" -> Pos(head.x, head.y - 1)
                    else -> exitProcess(0) // Very fishy
                }

                knots.forEachIndexed { idx, knot ->

                    val relPos = if (idx == 0) {
                        Pos(head.x - knot.x, head.y - knot.y)
                    } else {
                        Pos(knots[idx - 1].x - knot.x, knots[idx - 1].y - knot.y)
                    }

                    knots[idx] = if (relPos.x == 2 && relPos.y == 0) {
                        Pos(knot.x + 1, knot.y)
                    } else if (relPos.x == -2 && relPos.y == 0) {
                        Pos(knot.x - 1, knot.y)
                    } else if (relPos.x == 0 && relPos.y == 2) {
                        Pos(knot.x, knot.y + 1)
                    } else if (relPos.x == 0 && relPos.y == -2) {
                        Pos(knot.x, knot.y - 1)
                    } else if (relPos.x == 2 && relPos.y == 1) {
                        Pos(knot.x + 1, knot.y + 1)
                    } else if (relPos.x == 2 && relPos.y == -1) {
                        Pos(knot.x + 1, knot.y - 1)
                    } else if (relPos.x == -1 && relPos.y == 2) {
                        Pos(knot.x - 1, knot.y + 1)
                    } else if (relPos.x == 1 && relPos.y == 2) {
                        Pos(knot.x + 1, knot.y + 1)
                    } else if (relPos.x == -2 && relPos.y == 1) {
                        Pos(knot.x - 1, knot.y + 1)
                    } else if (relPos.x == -2 && relPos.y == -1) {
                        Pos(knot.x - 1, knot.y - 1)
                    } else if (relPos.x == -1 && relPos.y == -2) {
                        Pos(knot.x - 1, knot.y - 1)
                    } else if (relPos.x == 1 && relPos.y == -2) {
                        Pos(knot.x + 1, knot.y - 1)
                    } else if (relPos.x > 2 || relPos.y > 2) {
                        exitProcess(0)
                    }
                    else {
                        knot
                    }
                }

                tailPositions.add(knots.last())
            }

        }

        return tailPositions.toSet().size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    val testInput2 = readInput("Day09_test2")
    check(part1(testInput) == 13)
//    check(part2(testInput) == 1)
    check(part2(testInput2) == 36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}