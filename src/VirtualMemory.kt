@file: JvmName("main")
import java.io.File
import kotlin.random.Random

fun main(args: Array<String>) {
    require(args.size >= 3) {"Arguments can't be < 3"}
    val (n, m, opt_size) = args.map{it.toInt()}.take(3)
    var once = 1000
    if(args.size > 3) {
        once = args[3].toInt()
    }
    val (fifo, lru, opt) = listOf(FIFO(), LRU(), OPT())
    var queriesNum = 0
    val swapsFile: File
    val statsFile: File
    fifo.init(n)
    lru.init(n)
    opt.init(n)

    val queries = MutableList<Int>(opt_size) { Random.nextInt(1, n + 1) }
    queries.forEach { opt.addQuery(it) }

    try {
        swapsFile = File("swaps.txt")
        statsFile = File("statistics.txt")
        swapsFile.writeText("")
        statsFile.writeText("")
    } catch (e: Exception) {
        println("Some problems with files swaps.txt and stastistics.txt")
        return
    }

    try {
        swapsFile.appendText(firstLine())
        statsFile.appendText(firstLine())
    } catch (e: Exception) {
        println("Some problems with files swaps.txt and stastistics.txt")
    }
    print(firstLine())
    while(true) {
        queriesNum++
        queries.add(Random.nextInt(1, n + 1))
        val currentQuery = queries.first()
        queries.removeFirst()
        opt.addQuery(queries.last())
        val (FIFOswap, LRUswap, OPTswap) = listOf(fifo.getSwap(m, currentQuery), lru.getSwap(m, currentQuery), opt.getSwap(m, currentQuery))
        try {
            printSwaps(FIFOswap, LRUswap, OPTswap, swapsFile)
        } catch (e: Exception) {
            println("Something went wrong!  (swaps)")
        }
        if(once != -1 && queriesNum % once == 0) {
            try {
                printStatistics(fifo.swaps, lru.swaps, opt.swaps, queriesNum, statsFile)
            } catch (e: Exception) {
                println("Something went wrong! (statistic)")
            }
        }
    }
}