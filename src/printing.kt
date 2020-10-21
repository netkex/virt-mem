private const val lineSize = 25 // size for one algorithm line

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

/**
 * println one line with all algotirhms action on current step
 */
private fun printLine(FIFOaction: Int, LRUaction: Int, OPTaction: Int) {
    val FIFOline = getMessage(FIFOaction)
    val LRUline = getMessage(LRUaction)
    val OPTline = getMessage(OPTaction)
    println("${FIFOline.padEnd(lineSize, ' ')} | " +
            "${LRUline.padEnd(lineSize, ' ')} |" +
            OPTline.padEnd(lineSize, ' '))
}


/**
 * print result of algorithms and number of swaps in each algorithm in the end
 * @param [FIFOchanges] FIFO algorithm results
 * @param [LRUchanges] LRU algorithm results
 * @param [OPTchanges] OPT algorithm results
 */
fun print(FIFOchanges: List<Int>, LRUchanges: List<Int>, OPTchanges: List<Int>, FIFOswaps: Int, LRUswaps: Int, OPTswaps: Int) {

    //ansFIFO, ansLRU, ansOPT must be same size, because they based on same queries(req)
    if(FIFOchanges.size != LRUchanges.size || LRUchanges.size != OPTchanges.size) {
        println("sorry, something went wrong")
        return
    }

    //print name of each column in the table
    println("${"FIFO".padEnd(lineSize, ' ')} | ${"LRU".padEnd(lineSize, ' ')} |" +
            "OPT".padEnd(lineSize, ' '))

    for (ansNum in FIFOchanges.indices) {
        printLine(FIFOchanges[ansNum], LRUchanges[ansNum], OPTchanges[ansNum])
    }

    //print number of swaps for each algorithm
    println("Number of swaps: ")
    println("${FIFOswaps.toString().padEnd(lineSize)} | ${LRUswaps.toString().padEnd(lineSize)} | " +
            OPTswaps.toString().padEnd(lineSize))

    //print some empty lines to divide different results of different input
    println("\n\n")
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