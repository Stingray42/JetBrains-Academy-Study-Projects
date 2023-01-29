class InvertedIndex<T>(val tokenizer: (T) -> Collection<String>) {
    private val data: MutableList<T> = mutableListOf()
    private val index: MultiMap<String, Int> = mutableMapOf()

    fun addAll(entities: Collection<T>) {
        data.addAll(entities)
        data.forEachIndexed { i, entry ->
            val tokens = tokenizer(entry)
            tokens.forEach {
                index.compute(it.toLowerCase()) { _, v ->
                    if (v != null) {
                        v.add(i)
                        v
                    } else {
                        mutableSetOf(i)
                    }
                }
            }
        }
    }

    fun find(query: Collection<String>, strategy: SearchStrategy): List<T> {
        return strategy.apply(index, query).map { data[it] }
    }
}