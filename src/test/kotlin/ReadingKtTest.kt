import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class checkIntegers {
    @Test
    fun `check empty list`() {
        assertEquals(true, checkIntegers(listOf<String>()))
    }

    @Test
    fun `check normal input`() {
        assertEquals(true, checkIntegers(listOf("123", "1", "2", "9999999")))
        assertEquals(true, checkIntegers(listOf("1", "5", "100")))
    }

    @Test
    fun `check non-positive integers`() {
        assertEquals(false, checkIntegers(listOf("-5", "7", "9")))
        assertEquals(false, checkIntegers(listOf("1", "7", "-130")))
    }

    @Test
    fun `check string in list`() {
        assertEquals(false, checkIntegers(listOf("abc", "7", "9")))
    }
}

internal class checkInput {
    @Test
    fun `check empty list`() {
        assertEquals(2, checkInput(listOf<String>()))
    }

    @Test
    fun `check normal input`() {
        assertEquals(0, checkInput(listOf("2", "1", "1", "2", "2", "1")))
        assertEquals(0, checkInput(listOf("100", "1", "0")))
    }

    @Test
    fun `less then 3 integers`() {
        assertEquals(2, checkInput(listOf("1")))
        assertEquals(2, checkInput(listOf("1", "2")))
    }

    @Test
    fun `string in input`() {
        assertEquals(1, checkInput(listOf("abc", "1", "3")))
        assertEquals(1, checkInput(listOf("-1", "0", "500a")))
    }

    @Test
    fun `n is zero`() {
        assertEquals(3, checkInput(listOf("0", "2", "2")))
    }

    @Test
    fun `m is zero`() {
        assertEquals(3, checkInput(listOf("1", "0", "1", "2")))
    }

    @Test
    fun `m more than n`() {
        assertEquals(4, checkInput(listOf("1", "2", "1", "2")))
        assertEquals(4, checkInput(listOf("99", "100", "0")))
    }

    @Test
    fun `one of query more than n`() {
        assertEquals(5, checkInput(listOf("100", "2", "0", "2", "101")))
        assertEquals(5, checkInput(listOf("100", "6", "1", "201", "1")))
    }

    @Test
    fun `one of query is 0`() {
        assertEquals(5, checkInput(listOf("100", "6", "1", "0", "5")))
    }

    @Test
    fun `incorrect chartFlag`() {
        assertEquals(3, checkInput(listOf("2", "1", "4")))
    }
}