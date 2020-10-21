import java.io.File

/**
 * read lines (with spaces!) from file
 */
fun rawReadFile(filename: String): List<String> = File(filename).readLines().filter{it != ""}

/**
 * read strings from file without spaces
 */
fun readFile(filename: String): List<String> {
    val inp = rawReadFile(filename)
    return inp.fold(listOf()) {ans, str -> ans + str.split(" ").filter{it != ""}}
}

/**
 * check all strings are integers
 */
fun checkIntegers(input: List<String>): Boolean {
    return input.all{it.toIntOrNull() != null && it.toInt() >= 0}
}

/**
 * check input [input] for correctness
 * @return a number of error
 * 1 if input contains non-positive integers or strings
 * 2 if input size < 3 (at least must be n, m, chartFlag)
 * 3 if n or m or plotFlag are incorrect
 * 4 if n <= m
 * 5 if number one of requesting page is more than n
*/
fun checkInput(input: List<String>) : Int {
    if(!checkIntegers(input))
        return 1
    if(input.size < 3)
        return 2
    val (n, m, chartFlag) = input.take(3).map {it.toInt()}
    if(n == 0 || m == 0 || chartFlag > 3)
        return 3
    if(n <= m)
        return 4
    if(input.drop(3).any{it.toInt() > n || it.toInt() == 0})
        return 5
    return 0
}
