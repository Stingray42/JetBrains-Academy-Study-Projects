import java.io.File
import kotlin.system.exitProcess

typealias MultiMap<K, V> = MutableMap<K, MutableCollection<V>>

fun main(args: Array<String>) {
    val path = getFilePath(args)
    val file = File(path)
    if (!file.isFile) {
        exit("'$file' is not a file!", -1)
    }

    val data = file.readLines().map { Person.parse(it) }
    val index = InvertedIndex<Person> {
        it.toString().trim().toLowerCase().split(" ")
    }
    index.addAll(data)

    loop@ while (true) {
        println("""
        === Menu ===
        1. Find a person
        2. Print all persons
        0. Exit
        """.trimIndent())
        when (Input.nextInt()) {
            1 -> search(index)
            2 -> list(data)
            0 -> break@loop
            else -> println("Incorrect option! Try again.")
        }
    }
    println("Bye!")
}

fun getFilePath(args: Array<String>): String {
    val pathKey = "--data"
    if (!args.contains(pathKey)) {
        exit("'$pathKey' argument is required", 2)
    }
    return args[args.indexOf(pathKey) + 1]
}

fun search(index: InvertedIndex<*>) {
    val searchStrategy = selectStrategy()
    println("Enter a name or email to search all suitable people.")
    val query = Input.nextLine().trim().split(" ")
    val result = index.find(query, searchStrategy)
    if (result.isNullOrEmpty()) {
        println("No matching people found.")
    } else {
        println("${result.size} persons found:")
        result.forEach(::println)
    }
}

fun selectStrategy(): SearchStrategy {
    while (true) {
        println("Select a matching strategy: ${SearchStrategy.values().joinToString(", ")}")
        val option = Input.nextLine()
        val strategy = SearchStrategy.select(option.toUpperCase())
        if (strategy != null) {
            return strategy
        }
        println("Unknown option: $option")
    }
}

fun list(data: List<*>) {
    println("=== List of persons ===")
    data.forEach(::println)
}

fun exit(message: String, code: Int) {
    println(message)
    exitProcess(code)
}
