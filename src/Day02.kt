fun main() {
    val forward = "forward"
    val down = "down"
    val up = "up"
    val depth = "depth"
    val aim = "aim"
    val commandIndex = 0;
    val commandValueIndex = 1;

    fun part1(inputs: List<String>): Long {
        val commandMap: MutableMap<String, Long> = mutableMapOf()
        commandMap[forward] = 0L
        commandMap[down] = 0L
        commandMap[up] = 0L

        inputs.forEach {
            val input: List<String> = it.split(" ")
            val command = input[commandIndex]
            val commandValue = input[commandValueIndex]

            commandMap[command] = commandValue.toLong() + commandMap[command]!!
        }

        return (commandMap[down]!! - commandMap[up]!!) * commandMap[forward]!!
    }

    fun part2(inputs: List<String>): Long {
        val commandMap: MutableMap<String, Long> = mutableMapOf()
        commandMap[forward] = 0L
        commandMap[aim] = 0L
        commandMap[depth] = 0L

        inputs.forEach {
            val input: List<String> = it.split(" ")

            val command = input[commandIndex]
            val commandValue = input[commandValueIndex].toLong()

            when (command) {
                forward -> {
                    commandMap[forward] = commandMap[forward]!! + commandValue
                    commandMap[depth] = commandMap[depth]!! + commandMap[aim]!! * commandValue
                }
                down -> {
                    commandMap[aim] = commandMap[aim]!! + commandValue
                }
                up -> {
                    commandMap[aim] = commandMap[aim]!! - commandValue
                }
            }
        }

        return commandMap[forward]!! * commandMap[depth]!!
    }

    // test if implementation meets criteria from the description, like:
    val testInputPart1 = readInput("Day02_01_test")
    val testInputPart2 = readInput("Day02_02_test")
    check(part1(testInputPart1) == 150L)
    check(part2(testInputPart2) == 900L)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
