@file: JvmName("main")

fun runAlgorithms(inputFile: String) {
    //checkFile
    val rawInput: List<String>
    try {
        rawInput = readFile(inputFile)
    } catch (e: Exception) {
        println("Reading file '$inputFile' Error")
        println(e)
        return
    }
    val inputErrors = checkInput(rawInput)
    if (inputErrors != 0) {
        printErrors(inputErrors)
        return
    }
    val input = rawInput.map { it.toInt() }
    val (n, m, chartFlag) = input.take(3)
    val queries = input.drop(3)
    val ansFIFO = FIFO().getSwaps(n, m, queries)
    val ansLRU = LRU().getSwaps(n, m, queries)
    val ansOPT = OPT().getSwaps(n, m, queries)
    print(ansFIFO, ansLRU, ansOPT)
    try {
        draw(ansFIFO, ansLRU, ansOPT, inputFile, chartFlag)
    }
    catch (e: Exception) {
        println("Drawing chart '$inputFile' Error")
        return
    }
}


fun main(args: Array<String>) {
    require(args.isNotEmpty()) {"Arguments can't be empty"}
    args.forEach { runAlgorithms(it) }
}