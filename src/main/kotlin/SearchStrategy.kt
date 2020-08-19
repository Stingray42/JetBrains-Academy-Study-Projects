enum class SearchStrategy {
    ANY {
        override fun apply(index: MultiMap<String, Int>, query: Collection<String>): Collection<Int> {
            return query.flatMap { index[it].orEmpty() }
        }
    },
    ALL {
        override fun apply(index: MultiMap<String, Int>, query: Collection<String>): Collection<Int> {
            return query.map { index[it].orEmpty() }.reduce { acc, set -> acc.intersect(set) }
        }
    },
    NONE {
        override fun apply(index: MultiMap<String, Int>, query: Collection<String>): Collection<Int> {
            return index.values.flatten().subtract(ANY.apply(index, query))
        }
    };

    abstract fun apply(index: MultiMap<String, Int>, query: Collection<String>): Collection<Int>

    companion object {
        fun select(name: String): SearchStrategy? {
            return values().find { it.name == name }
        }
    }
}