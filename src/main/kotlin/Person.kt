data class Person(val firstName: String,
                  val secondName: String?,
                  val email: String?) {

    companion object {
        fun parse(input: String): Person {
            val tokens = input.split(" ")
            if (tokens.size > 3) {
                error(tokens)
            }
            val secondName = if (tokens.size > 1) tokens[1] else null
            val email = if (tokens.size > 2) tokens[2] else null

            return Person(tokens[0], secondName, email)
        }
    }

    override fun toString(): String {
        return "$firstName ${secondName.orEmpty()} ${email.orEmpty()}".trim()
    }
}