@file: JvmName("main")
import kotlin.random.Random

fun generateQueries(n: Int, sz: Int): List<Int> {
    return List(sz) { Random.nextInt(0, n + 1) }
}

fun main(args: Array<String>) {
    require(args.isNotEmpty()) {"Arguments can't be empty"}
    val (n, m, opt_size) = args.map{it.toInt()}.take(3)
    val (fifo, lru, opt) = listOf(FIFO(), LRU(), OPT())
    fifo.init(n)
    lru.init(n)
    opt.init(n)

    while(true) {
        val newQueries = generateQueries(n, opt_size)
        val (FIFOchanges, FIFOswaps) = fifo.getSwaps(m, newQueries)
        val (LRUchanges, LRUswaps) = lru.getSwaps(m, newQueries)
        val (OPTchanges, OPTswaps) = opt.getSwaps(m, newQueries)
        print(FIFOchanges, LRUchanges, OPTchanges, FIFOswaps, LRUswaps, OPTswaps)
    }
}