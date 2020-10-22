@file: JvmName("main")
import kotlin.random.Random

fun generateQueries(n: Int, sz: Int): List<Int> {
    return List(sz) { Random.nextInt(0, n + 1) }
}

fun main(args: Array<String>) {
    require(args.size >= 3) {"Arguments can't be < 3"}
    val (n, m, opt_size) = args.map{it.toInt()}.take(3)
    var once = 1000
    if(args.size > 3) {
        once = args[3].toInt()
    }
    val (fifo, lru, opt) = listOf(FIFO(), LRU(), OPT())
    var queriesNum = 0
    fifo.init(n)
    lru.init(n)
    opt.init(n)

    val queries = MutableList<Int>(opt_size) { Random.nextInt(1, n + 1) }
    queries.forEach { opt.addQuery(it) }

    while(true) {
        queriesNum++
        queries.add(Random.nextInt(1, n + 1))
        val currentQuery = queries.first()
        queries.removeFirst()

        opt.addQuery(queries.last())
        printLine(fifo.getSwap(m, currentQuery), lru.getSwap(m, currentQuery), opt.getSwap(m, currentQuery))
        if(queriesNum % once == 0) {
            println()
            println("Current amount of queries: $queriesNum")
            println("${fifo.swaps.toString().padEnd(lineSize)} | ${lru.swaps.toString().padEnd(lineSize)} | " +
                    opt.swaps.toString().padEnd(lineSize))
            println("in percent")
            println("${(100.0 - fifo.swaps * 100.0 / queriesNum).toString().padEnd(lineSize)} | ${(100.0 - lru.swaps  * 100.0 / queriesNum).toString().padEnd(lineSize)} | " +
                    (100.0 - opt.swaps * 100.0 / queriesNum).toString().padEnd(lineSize))
            println()
        }
    }
}