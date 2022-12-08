data class File(val name: String, val size: Int)
class Folder(val value: String, val parent : Folder?) {
    val files: MutableList<File> = mutableListOf()
    val children: MutableList<Folder> = mutableListOf()
    fun add(child: Folder) = children.add(child)
    fun addFile(file: File) = files.add(file)

    fun returnAllFolders(): List<Folder> {
        val folders = mutableListOf<Folder>()
        addAllFolders(this, folders)
        return folders
    }

    private fun addAllFolders(folder: Folder, folders: MutableList<Folder>) {
        folders.add(folder)
        for (child in folder.children) {
            addAllFolders(child, folders)
        }
    }

    fun size() : Int {
        return files.sumOf { it.size } + children.sumOf { it.size() }
    }

    override fun toString(): String {
        val buffer = StringBuilder(50)
        print(buffer, "", "")
        return buffer.toString()
    }

    private fun print(buffer: StringBuilder, prefix: String, childrenPrefix: String) {
        buffer.append(prefix)
        buffer.append(value)
        buffer.append('\n')
        val it: Iterator<Folder> = children.iterator()
        while (it.hasNext()) {
            val next: Folder = it.next()
            if (it.hasNext()) {
                next.print(buffer, "$childrenPrefix├── ", "$childrenPrefix│   ")
            } else {
                next.print(buffer, "$childrenPrefix└── ", "$childrenPrefix    ")
            }
        }
    }
}


fun main() {

    fun buildTreeStructure(input: List<String>): Folder {

        val rootFolder = Folder("/", null)
        var currentFolder = rootFolder
        // Removes first cd
        val zeInput = input.drop(1)

        val lineIterator = zeInput.listIterator()
        while (lineIterator.hasNext()) {
            val next = lineIterator.next()
            if(next == "$ ls"){

                var nextnext = lineIterator.next()
                while(!nextnext.startsWith("$ cd")){

                    if(nextnext.startsWith("dir")){
                        val folderName = nextnext.removePrefix("dir").trim()
                        val newFolder = Folder(folderName, currentFolder)
                        currentFolder.add(newFolder)

                    }
                    else{
                        val fileInfo = nextnext.split(" ")
                        currentFolder.addFile(File(fileInfo[1], fileInfo[0].toInt()))
                    }

                    if(lineIterator.hasNext()) {
                        nextnext = lineIterator.next()
                    }
                    else{
                        return rootFolder
                    }
                }

                lineIterator.previous()
            }
            else if(next.startsWith("$ cd")) {
                val nextFolder = next.removePrefix("$ cd").trim()
                currentFolder = when (nextFolder) {
                    ".." -> {
                        currentFolder.parent!!
                    }
                    "/" -> {
                        rootFolder
                    }
                    else -> {
                        currentFolder.children.first { it.value == nextFolder }
                    }
                }
            }
        }

        return rootFolder
    }

    fun part1(input: List<String>): Int {

        val folder = buildTreeStructure(input)
        return folder
            .returnAllFolders()
            .filter { it.value != "/" }.filter { it.size() < 100000 }.sumOf { it.size() }
    }

    fun part2(input: List<String>): Int {
        val totalSize = 70000000
        val spaceNeeded = 30000000

        val folder = buildTreeStructure(input)

        val currentSize = folder.returnAllFolders().first { it.value == "/" }.size()
        val unusedSpace = totalSize - currentSize

        val smallestFolder =  folder
            .returnAllFolders()
            .filter { it.value != "/" }
            .sortedBy{ it.size()}
            .first { totalSize - (currentSize - it.size()) > spaceNeeded }


           return smallestFolder.size()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}