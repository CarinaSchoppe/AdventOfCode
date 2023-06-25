import kotlin.properties.Delegates

fun main() {

    val items = object {}.javaClass.getResourceAsStream("challenge3.txt")?.bufferedReader()?.readLines()
        ?.map { line -> Pair(line.take(line.length / 2), line.takeLast(line.length / 2)) }

    val values = mutableListOf<Char>()
    items?.forEach {
        val left = it.first
        val right = it.second
        values.add(right.find { char -> left.contains(char) }!!)
    }
    println(values)

    val map = ('a'..'z').zip(1..26).toMap() + ('A'..'Z').zip(27..52).toMap()

    val sum = values.map { map[it] }.sumOf { it!! }
    println(sum)
    println(seperator(items!!, map))
}

fun seperator(input: List<Pair<String, String>>, map: Map<Char, Int>): Int {

    val items = mutableListOf<List<String>>()
    var sum = 0
    for (i in input.indices step 3) {
        val liste = mutableListOf<String>()
        liste.add(input[i].first + input[i].second)
        liste.add(input[i + 1].first + input[i + 1].second)
        liste.add(input[i + 2].first + input[i + 2].second)
        items.add(liste)
    }
    items.forEach {
        val line = it[0]
        var value by Delegates.notNull<Char>()
        for (char in line) {
            if (it[1].contains(char) && it[2].contains(char)) {
                value = char
                break
            }
        }
        sum += map[value]!!
    }
    return sum

}