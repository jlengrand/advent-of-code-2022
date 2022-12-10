fun main() {
    fun part1(input: List<String>): Int {

        val width = input.first().length
        val height = input.size

        val grid = Array(height) { IntArray(width)}
        val visibleGrid = Array(height) { IntArray(width)}

        input.forEachIndexed { idx, line ->
            line.forEachIndexed { index, tree ->
                grid[idx][index] = tree.toString().toInt()
            }
        }

        grid.forEachIndexed { indexc, row ->
            row.forEachIndexed { indexr, tree ->

                // makes column
                val column = grid.map { it[indexr] }

                visibleGrid[indexc][indexr] = 0 // invisible by default

                // Tree is on the side
                if(indexr == 0 || indexc == 0 || indexc == visibleGrid.size - 1 || indexr == row.size - 1) {
                    visibleGrid[indexc][indexr] = 1 // visible
                }
                // Visible from the left
                else if(row.slice(IntRange(0, indexr - 1)).all{ it < tree}){
                    val slice = row.slice(IntRange(0, indexr - 1 ))
                    visibleGrid[indexc][indexr] = 1 // visible
                }
                // Visible from the right
                else if(row.slice(IntRange(indexr + 1, row.size - 1)).all{ it < tree}){
                    val slice = row.slice(IntRange(indexr + 1, row.size - 1))
                    visibleGrid[indexc][indexr] = 1 // visible
                }
                // Visible from the top
                else if(column.slice(IntRange(0, indexc - 1)).all{ it < tree}){
                    val slice = column.slice(IntRange(0, indexc - 1 ))
                    visibleGrid[indexc][indexr] = 1 // visible
                }
                // Visible from the bottom
                else if(column.slice(IntRange(indexc + 1, row.size - 1)).all{ it < tree}){
                    val slice = column.slice(IntRange(indexc + 1, row.size - 1))
                    visibleGrid[indexc][indexr] = 1 // visible
                }

            }
        }

        return visibleGrid.sumOf { it.sumOf { vis -> vis } }
    }

    fun part2(input: List<String>): Int {

        data class Viewing(val top: Int, val bot: Int, val left: Int, val right: Int) {
            fun score() = top * bot * left * right
        }

        val width = input.first().length
        val height = input.size

        val grid = Array(height) { IntArray(width) }
        val visibleGrid = Array(height) { Array(height) { Viewing(0, 0, 0, 0) } }

        input.forEachIndexed { idx, line ->
            line.forEachIndexed { index, tree ->
                grid[idx][index] = tree.toString().toInt()
            }
        }

        grid.forEachIndexed { indexc, row ->
            row.forEachIndexed { indexr, tree ->

                // makes column
                val column = grid.map { it[indexr] }

                //left
                val leftslice = row.slice(IntRange(0, indexr - 1)).reversed()
                val leftornot = leftslice.indexOfFirst { it >= tree }
                val left = if (leftslice.isEmpty()) {
                    0
                } else if (leftornot == -1) {
                    leftslice.size
                } else {
                    leftornot + 1
                }

                // right
                val rightslice = row.slice(IntRange(indexr + 1, row.size - 1))
                val rightornot = rightslice.indexOfFirst { it >= tree }
                val right = if (rightslice.isEmpty()) {
                    0
                } else if (rightornot == -1) {
                    rightslice.size
                } else {
                    rightornot + 1
                }

                // top
                val topslice = column.slice(IntRange(0, indexc - 1)).reversed()
                val topornot = topslice.indexOfFirst { it >= tree }
                val top = if (topslice.isEmpty()) {
                    0
                } else if (topornot == -1) {
                    topslice.size
                } else {
                    topornot + 1
                }

                // bot
                val botslice = column.slice(IntRange(indexc + 1, row.size - 1))
                val botornot = botslice.indexOfFirst { it >= tree }
                val bot = if (botslice.isEmpty()) {
                    0
                } else if (botornot == -1) {
                    botslice.size
                } else {
                    botornot + 1
                }

                visibleGrid[indexc][indexr] = Viewing(top, bot, left, right)

            }
        }

        val scores = visibleGrid.map { row -> row.map { it.score() } }.flatten()

        return scores.maxOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
