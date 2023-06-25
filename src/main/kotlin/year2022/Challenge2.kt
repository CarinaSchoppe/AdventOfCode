fun main() {
    val lines = object {}.javaClass.getResourceAsStream("challenge2.txt")?.bufferedReader()?.readLines()
    val rounds = lines?.map {
        Pair(it.split(" ")[0], it.split(" ")[1])
    }
    val sum = part1(rounds!!)
    val sum2 = part2(rounds)
    println(sum)
    println(sum2)
}

fun part1(rounds: List<Pair<String, String>>): Int {
    var sum = 0
    rounds.forEach {
        val opponent = it.first
        val mine = it.second
        //mine, opponent
        //x,a = rock, b,y = paper, c,z = sissors
        //win + 6p, draw +3 points, lose + 0 points
        sum += when (mine to opponent) {
            "X" to "C" -> 6
            "X" to "B" -> 0
            "Y" to "A" -> 6
            "Y" to "C" -> 0
            "Z" to "A" -> 0
            "Z" to "B" -> 6
            else -> 3
        }
        sum += when (mine) {
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> 0
        }

    }
    return sum
}


//A,B,C - rock paper sissors
fun part2(rounds: List<Pair<String, String>>): Int {
    //X = lose y  = draw z = win

    var sum = 0
    rounds.forEach {
        val kind = it.second
        val opponent = it.first
        sum += when (kind) {
            "X" -> 0
            "Y" -> 3
            "Z" -> 6
            else -> 0
        }
        sum += when (opponent to kind) {

            "A" to "X" -> 3
            "A" to "Y" -> 1
            "A" to "Z" -> 2
            "B" to "X" -> 1
            "B" to "Y" -> 2
            "B" to "Z" -> 3
            "C" to "X" -> 2
            "C" to "Y" -> 3
            "C" to "Z" -> 1
            else -> 0
        }
    }
    return sum

}



