import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class `check toNumOfSwaps` {

    @Test
    fun `empty`() {
        assertEquals(listOf<Int>(), toNumOfSwaps(listOf()))
    }

    @Test
    fun `zero swaps`() {
        assertEquals(listOf(0.0), toNumOfSwaps(listOf(-2)))
        assertEquals(listOf(0.0), toNumOfSwaps(listOf(-1)))
    }

    @Test
    fun `one element`() {
        assertEquals(listOf(1.0), toNumOfSwaps(listOf(1)))
        assertEquals(listOf(1.0), toNumOfSwaps(listOf(8)))
    }

    @Test
    fun `random elements`() {
        assertEquals(listOf(0.0, 1.0, 1.0, 1.0, 2.0), toNumOfSwaps(listOf(-1, 2, -1, -2, 3)))
        assertEquals(listOf(1.0, 2.0, 2.0, 2.0, 3.0, 4.0, 4.0), toNumOfSwaps(listOf(2, 4, -1, -2, 5, 6, -2)))
    }
}


internal class `check toSwapPercent` {

    @Test
    fun `empty`() {
        assertEquals(listOf<Int>(), toSwapPercent(listOf()))
    }

    @Test
    fun `zero swaps`() {
        assertEquals(listOf(100.0), toSwapPercent(listOf(-2)))
        assertEquals(listOf(100.0), toSwapPercent(listOf(-1)))
    }

    @Test
    fun `one element`() {
        assertEquals(listOf(0.0), toSwapPercent(listOf(1)))
        assertEquals(listOf(0.0), toSwapPercent(listOf(8)))
    }

    @Test
    fun `random elements`() {
        assertEquals(listOf(1.0 * 100 / 1, 1.0 * 100 / 2, 2.0 * 100 / 3, 3.0 * 100 / 4, 3.0 * 100 / 5),
            toSwapPercent(listOf(-1, 2, -1, -2, 3)))
        assertEquals(listOf(0.0 * 100 / 1, 0.0 * 100 / 2, 1.0 * 100/ 3,
            2.0 * 100/ 4, 2.0 * 100 / 5, 2.0 * 100 / 6, 3.0 * 100 / 7),
            toSwapPercent(listOf(2, 4, -1, -2, 5, 6, -2)))
    }
}
