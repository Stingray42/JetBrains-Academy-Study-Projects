package coffee.machine

import java.util.*

val scanner = Scanner(System.`in`)

fun main() {
    val supplies = Consumables(400, 540, 120, 9)
    val coffeeMachine = CoffeeMachine(supplies, 550)
    while (true) {
        println(coffeeMachine.state.message)
        coffeeMachine.read(scanner.next())
    }
}
