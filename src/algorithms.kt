const val AlreadyInMemory = -1 // constant shows that page is already in memory
const val JustAddToMemory = -2 // constant shows that page was just added to memory

open class Algorithm {
    var isCashed = BooleanArray(0) // array shows that page is in memory or not
    val cashedPages = mutableListOf<Int>()

    /**
     * check page in memory
     * @return true if page is in memory false in other case
     */
    private fun checkInMemory(page: Int) = isCashed[page]

    /**
     * initialisation
     */
    open fun init(n: Int, queries: List<Int>) { }

    /**
     * update page property
     */
    open fun updatePage(page: Int) {}

    /**
     * add page into memory
     */
    open fun addPage(page: Int) {}

    /**
     * find page which must be replaced and replace it
     * @return replacement page
     */
    open fun getReplacementPage(): Int {return 0}


    /**
     * get swaps in memory by algorithm
     * @return list of swaps(x) where
     *  x > 0 - swap with page x
     *  x = -1([AlreadyInMemory]) - page is already in memory
     *  x = -2([JustAddToMemory]) - page is just added to memory
     */
    fun getSwaps(n: Int, m: Int, queries: List<Int>): List<Int> {
        val ans = mutableListOf<Int>()
        init(n, queries)
        for (newPage in queries) {
            updatePage(newPage)
            if(checkInMemory(newPage)) {
                ans.add(AlreadyInMemory)
                continue
            }
            if(cashedPages.size < m) {
                addPage(newPage)
                ans.add(JustAddToMemory)
                continue
            }
            val replacementPage = getReplacementPage()
            ans.add(replacementPage)
            addPage(newPage)
        }
        return ans
    }
}

class FIFO : Algorithm() {
    override fun init(n: Int, queries: List<Int>) {
        isCashed = BooleanArray (n + 1) {false}
        cashedPages.clear()
    }
    override fun addPage(page: Int) {
        isCashed[page] = true
        cashedPages.add(page)
    }
    override fun getReplacementPage(): Int {
        val ans = cashedPages.first()
        cashedPages.removeFirst()
        isCashed[ans] = false
        return ans
    }
}

class LRU : Algorithm() {
    private var timer = 0
    private var lastAppeal = IntArray(0)
    override fun init(n: Int, queries: List<Int>) {
        lastAppeal = IntArray(n + 1) {-1} // -1 means page is out of cash
        cashedPages.clear()
        isCashed = BooleanArray(n + 1) { false }
        timer = 0
    }
    override fun updatePage(page: Int) {
        lastAppeal[page] = timer++ // updating last appeal of this page
    }
    override fun addPage(page: Int) {
        isCashed[page] = true
        cashedPages.add(page)
    }
    override fun getReplacementPage(): Int {
        var ans = cashedPages.first()
        for (page in cashedPages) {  // find page with the oldest appeal
            if(lastAppeal[ans] > lastAppeal[page])
                ans = page
        }
        cashedPages.remove(ans)
        isCashed[ans] = false
        return ans
    }
}

class OPT : Algorithm() {
    private var pageQueries = listOf<MutableList<Int>>() // list of future queries for page
    private val infinity = Int.MAX_VALUE
    override fun init(n: Int, queries: List<Int>) {
        isCashed = BooleanArray(n + 1) {false}
        cashedPages.clear()
        pageQueries = List(n + 1) { mutableListOf() }
        for (operationNum in queries.indices) {
            pageQueries[queries[operationNum]].add(operationNum)
        }
        pageQueries.map{it.add(infinity)}
    }
    override fun updatePage(page: Int) {
        pageQueries[page].removeFirst()
    }
    override fun addPage(page: Int) {
        isCashed[page] = true
        cashedPages.add(page)
    }
    override fun getReplacementPage(): Int {
        var ans = cashedPages.first()
        for (page in cashedPages) { // find page with fhe furthest next query
            if(pageQueries[ans].first() < pageQueries[page].first() ||
                    (pageQueries[ans].first() == pageQueries[page].first() && ans > page))
                ans = page
        }
        isCashed[ans] = false
        cashedPages.remove(ans)
        return ans
    }
}