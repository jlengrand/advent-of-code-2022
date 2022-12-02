fun main() {

    // A rock
    // B paper
    // C scissor

    // X rock
    // Y paper
    // Z scissor

    fun getHandFromLine(line: String) : Int {
        return if(line.contains("X")){
            1
        } else if(line.contains("Y")){
            2
        } else{
            3
        }
    }

    fun getGameFromLine(line: String) : Int {
        return if(line == "A X"){
            3
        } else if(line.contains("A Y")){
            6
        } else if(line.contains("A Z")){
            0
        } else if(line.contains("B X")){
            0
        } else if(line.contains("B Y")){
            3
        } else if(line.contains("B Z")){
            6
        } else if(line.contains("C X")){
            6
        } else if(line.contains("C Y")) {
            0
        } else{
            3
        }
    }

    fun part1(input: List<String>): Int {

        // Could do this with a fold, but will do in two steps for step 2
        val shapes = input.map { getHandFromLine(it) }
        val games = input.map { getGameFromLine(it) }

        val scores = shapes.zip(games) { xv, yv -> xv + yv }

        return scores.sum()
    }

    fun part2(input: List<String>): Int {
        fun getGameFromLine(line: String) : Int {
            return if(line.contains("X")){
                0
            } else if(line.contains("Y")){
                3
            } else{
                6
            }
        }

        fun getHandFromLine(line: String) : Int {
            return if(line == "A X"){
                3
            } else if(line.contains("A Y")){
                1
            } else if(line.contains("A Z")){
                2
            } else if(line.contains("B X")){
                1
            } else if(line.contains("B Y")){
                2
            } else if(line.contains("B Z")){
                3
            } else if(line.contains("C X")){
                2
            } else if(line.contains("C Y")) {
                3
            } else{
                1
            }
        }

        val games = input.map { getGameFromLine(it) }
        val hands = input.map { getHandFromLine(it) }

        val scores = hands.zip(games) { xv, yv -> xv + yv }

        return scores.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}