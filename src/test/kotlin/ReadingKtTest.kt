import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ReadFile {
    @Test
    fun `read empty file`() {
        assertEquals(listOf<String>(), readFile("./data/inputTests/01.in"))
    }

    @Test
    fun `empty lines in input`() {
        assertEquals(listOf("1", "2", "3", "1", "2", "3"), readFile("./data/inputTests/02.in"))
        assertEquals(listOf("1", "2", "3"), readFile("./data/inputTests/03.in"))
    }

    @Test
    fun `tabs in input`() {
        assertEquals(listOf("1", "2", "3", "4", "10000000"), readFile("./data/inputTests/04.in"))
    }

    @Test
    fun `check empty list of integers`() {
        assertEquals(true, checkIntegers(listOf<String>()))
    }
}
