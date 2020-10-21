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
 * @param [ansFIFO] FIFO algorithm results
 * @param [ansLRU] LRU algorithm results
 * @param [ansOPT] OPT algorithm results
 */
fun print(ansFIFO: List<Int>, ansLRU: List<Int>, ansOPT: List<Int>) {

    //ansFIFO, ansLRU, ansOPT must be same size, because they based on same queries(req)
    if(ansFIFO.size != ansLRU.size || ansLRU.size != ansOPT.size) {
        println("sorry, something went wrong")
        return
    }

    var countFIFO = 0
    var countLRU = 0
    var countOPT = 0

    //print name of each column in the table
    println("${"FIFO".padEnd(lineSize, ' ')} | ${"LRU".padEnd(lineSize, ' ')} |" +
            "OPT".padEnd(lineSize, ' '))

    for (ansNum in ansFIFO.indices) {
        if (ansFIFO[ansNum] > 0) countFIFO++
        if (ansLRU[ansNum] > 0) countLRU++
        if (ansOPT[ansNum] > 0) countOPT++
        printLine(ansFIFO[ansNum], ansLRU[ansNum], ansOPT[ansNum])
    }

    //print number of swaps for each algorithm
    println("Number of swaps: ")
    println("${countFIFO.toString().padEnd(lineSize)} | ${countLRU.toString().padEnd(lineSize)} | " +
            countOPT.toString().padEnd(lineSize))

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