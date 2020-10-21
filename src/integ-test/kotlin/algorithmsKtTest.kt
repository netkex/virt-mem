import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class `FIFO testing` {
    @Test
    fun `check zero queries`() {
        assertEquals(listOf<Int>(), FIFO().getSwaps(100, 1, listOf()))
    }

    @Test
    fun `check one query`() {
        assertEquals(listOf(-2), FIFO().getSwaps(100, 1, listOf(1)))
        assertEquals(listOf(-2), FIFO().getSwaps(1000, 1, listOf(6)))
    }

    @Test
    fun `check m queries`() {
        assertEquals(listOf(-2), FIFO().getSwaps(100, 1, listOf(1)))
        assertEquals(listOf(-2, -2, -2, -2, -2), FIFO().getSwaps(1000, 5, listOf(6, 4, 7, 3, 1)))
    }

    @Test
    fun `check random queries`() {
        assertEquals(listOf(-2, -2, -2, 1, -1, 2, 3), FIFO().getSwaps(6, 3, listOf(1, 2, 3, 4, 4, 5, 6)))
        assertEquals(listOf(-2, -1, -2, -1, 1, 2, -1, 3, 1), FIFO().getSwaps(4, 2, listOf(1, 1, 2, 2, 3, 1, 3, 2, 4)))
        assertEquals(listOf(-2, -2, -2, -1, 1, 2, -1), FIFO().getSwaps(4, 3, listOf(1, 2, 3, 1, 4, 1, 3)))
        assertEquals(listOf(-2, -1, -2, 1, 2, -1, -1, 3, 1), FIFO().getSwaps(3, 2, listOf(1, 1, 2, 3, 1, 3, 1, 2, 3)))
        assertEquals(listOf(-2, -2, -2, 6, 2, -1, 5, 3), FIFO().getSwaps(7, 3, listOf(6, 2, 5, 3, 7, 7, 1, 6)))
    }
}

internal class `LRU testing` {
    @Test
    fun `check zero queries`() {
        assertEquals(listOf<Int>(), LRU().getSwaps(100, 1, listOf()))
    }

    @Test
    fun `check one query`() {
        assertEquals(listOf(-2), LRU().getSwaps(100, 1, listOf(1)))
        assertEquals(listOf(-2), LRU().getSwaps(1000, 1, listOf(6)))
    }

    @Test
    fun `check m queries`() {
        assertEquals(listOf(-2), LRU().getSwaps(100, 1, listOf(1)))
        assertEquals(listOf(-2, -2, -2, -2, -2), LRU().getSwaps(1000, 5, listOf(6, 4, 7, 3, 1)))
    }

    @Test
    fun `check random queries`() {
        assertEquals(listOf(-2, -2, -2, -1, -1, 3, 2, 1), LRU().getSwaps(6, 3, listOf(1, 2, 3, 2, 1, 4, 5, 6)))
        assertEquals(listOf(-2, -1, -2, -1, 1, 2, -1, 1, 3), LRU().getSwaps(4, 2, listOf(1, 1, 2, 2, 3, 1, 3, 2, 4)))
        assertEquals(listOf(-2, -2, -2, -2, -2, -1, 1, 3, 4, 5, 2, 6, 1),
                LRU().getSwaps(10, 5, listOf(1, 2, 3, 4, 5, 2, 6, 1, 7, 4, 3, 9, 10)))
        assertEquals(listOf(-2, -2, -2, 6, 2, -1, 5, 3), LRU().getSwaps(7, 3, listOf(6, 2, 5, 3, 7, 7, 1, 6)))
        assertEquals(listOf(-2, -2, -2, -1, -1, -1, -1, 6, -1),
                LRU().getSwaps(6, 3, listOf(1, 6, 4, 4, 1, 4, 1, 5, 4)))
    }
}

internal class `OPT testing` {
    @Test
    fun `check zero queries`() {
        assertEquals(listOf<Int>(), OPT().getSwaps(100, 1, listOf()))
    }

    @Test
    fun `check one query`() {
        assertEquals(listOf(-2), OPT().getSwaps(100, 1, listOf(1)))
        assertEquals(listOf(-2), OPT().getSwaps(1000, 1, listOf(6)))
    }

    @Test
    fun `check m queries`() {
        assertEquals(listOf(-2), OPT().getSwaps(100, 1, listOf(1)))
        assertEquals(listOf(-2, -2, -2, -2, -2), OPT().getSwaps(1000, 5, listOf(6, 4, 7, 3, 1)))
    }

    @Test
    fun `check random queries`() {
        assertEquals(listOf(-2, -2, -2, 3, -1, 1, 4, -1), OPT().getSwaps(6, 3, listOf(1, 2, 3, 4, 1, 5, 6, 2)))
        assertEquals(listOf(-2, -2, 1, -1, 2, 1), OPT().getSwaps(4, 2, listOf(1, 2, 3, 2, 1, 4)))
        assertEquals(listOf(-2, -2, -2, -2, -2, -1, 2, -1, 1, -1, -1, 3, 4),
                OPT().getSwaps(10, 5, listOf(1, 2, 3, 4, 5, 2, 6, 1, 7, 4, 3, 9, 10)))
        assertEquals(listOf(-2, -2, -2, 2, 3, -1, 5, -1), OPT().getSwaps(7, 3, listOf(6, 2, 5, 3, 7, 7, 1, 6)))
        assertEquals(listOf<Int>(-2, -2, -2, 1, -1, 6, -1, -1, 3, -1),
                OPT().getSwaps(6, 3, listOf(3, 6, 1, 2, 6, 4, 3, 4, 1, 2)))
    }
}