import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class `getMessage test` {

    @Test
    fun `page is in memory`() {
        assertEquals("page is already in memory", getMessage(-1))
    }

    @Test
    fun `add page in memory`() {
        assertEquals("add page in memory", getMessage(-2))
    }

    @Test
    fun `remove`() {
        assertEquals("remove 7 page", getMessage(7))
        assertEquals("remove 1212 page", getMessage(1212))
        assertEquals("remove 1 page", getMessage(1))
    }
}