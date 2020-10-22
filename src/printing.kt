import java.io.File

const val lineSize = 25 // size for one algorithm line
/**
 * transform action of algorithm [action] to string
 */
fun getMessage(action: Int): String {
    return when(action) {
        JustAddToMemory -> "add page in memory"
        AlreadyInMemory -> "page is already in memory"
        else -> "remove $action page"
    }
}

fun firstLine(): String {
    return "${"FIFO".padEnd(lineSize, ' ')} | ${"LRU".padEnd(lineSize, ' ')} |" +
            "OPT".padEnd(lineSize, ' ') + "\n"
}

/**
 * println one line with all algotirhms action on current step
 */
fun getLine(FIFOaction: Int, LRUaction: Int, OPTaction: Int): String {
    val FIFOline = getMessage(FIFOaction)
    val LRUline = getMessage(LRUaction)
    val OPTline = getMessage(OPTaction)
    return ("${FIFOline.padEnd(lineSize, ' ')} | " +
            "${LRUline.padEnd(lineSize, ' ')} |" +
            OPTline.padEnd(lineSize, ' '))
}

fun printSwaps(FIFOaction: Int, LRUaction: Int, OPTaction: Int, file: File) {
    val line = getLine(FIFOaction, LRUaction, OPTaction) + "\n"
    print(line)
    file.appendText(line)
}

fun printStatistics(FIFOswaps: Int, LRUswaps: Int, OPTswaps: Int, queriesNum: Int, file: File) {
    var stats = "\n"
    stats += "Current amount of queries: $queriesNum \n"
    stats += "${FIFOswaps.toString().padEnd(lineSize)} | ${LRUswaps.toString().padEnd(lineSize)} | " +
            OPTswaps.toString().padEnd(lineSize) +"\n"
    stats += "in percent \n"
    stats += "${(100.0 - FIFOswaps * 100.0 / queriesNum).toString().padEnd(lineSize)} | ${(100.0 - LRUswaps  * 100.0 / queriesNum).toString().padEnd(lineSize)} | " +
            (100.0 - OPTswaps * 100.0 / queriesNum).toString().padEnd(lineSize) + "\n\n"
    print(stats)
    file.appendText(stats)
}

fun printErrors(Errors: Int) {
    when (Errors) {
        1 -> println("Error: input contains non-positive integers or strings")
        2 -> println("Error: input size less than 3")
        3 -> println("Error: n or m  or plotFlag are incorrect")
        4 -> println("Error: n <= m")
        5 -> println("Error: number one of requesting page is incorrect")
    }
}