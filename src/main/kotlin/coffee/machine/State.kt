package coffee.machine

import kotlin.system.exitProcess

enum class State(val message: String) {
    READY("Write action (buy, fill, take, remaining, exit): > ") {
        override fun accept(input: String, coffeeMachine: CoffeeMachine) {
            when (input) {
                "buy" -> {
                    coffeeMachine.state = CHOOSING
                }
                "fill" -> {
                    coffeeMachine.state = FILLING_WATER
                }
                "take" -> {
                    coffeeMachine.withdraw()
                }
                "remaining" -> {
                    println(coffeeMachine.getState())
                }
                "exit" -> {
                    exitProcess(0)
                }
            }
        }
    },
    CHOOSING("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: > ") {
        override fun accept(input: String, coffeeMachine: CoffeeMachine) {
            when (input) {
                "1" -> {
                    coffeeMachine.make(Coffee.ESPRESSO)
                    coffeeMachine.state = READY
                }
                "2" -> {
                    coffeeMachine.make(Coffee.LATTE)
                    coffeeMachine.state = READY
                }
                "3" -> {
                    coffeeMachine.make(Coffee.CAPPUCCINO)
                    coffeeMachine.state = READY
                }
                "back" -> {
                    coffeeMachine.state = READY
                }
            }
        }
    },
    FILLING_WATER("Write how many ml of water do you want to add: > ") {
        override fun accept(input: String, coffeeMachine: CoffeeMachine) {
            coffeeMachine.supplies.water += input.toPositiveInt()
            coffeeMachine.state = FILLING_MILK
        }
    },
    FILLING_MILK("Write how many ml of milk do you want to add: > ") {
        override fun accept(input: String, coffeeMachine: CoffeeMachine) {
            coffeeMachine.supplies.milk += input.toPositiveInt()
            coffeeMachine.state = FILLING_BEANS
        }
    },
    FILLING_BEANS("Write how many grams of coffee beans do you want to add: > ") {
        override fun accept(input: String, coffeeMachine: CoffeeMachine) {
            coffeeMachine.supplies.beans += input.toPositiveInt()
            coffeeMachine.state = FILLING_CUPS
        }
    },
    FILLING_CUPS("Write how many disposable cups of coffee do you want to add: > ") {
        override fun accept(input: String, coffeeMachine: CoffeeMachine) {
            coffeeMachine.supplies.cups += input.toPositiveInt()
            coffeeMachine.state = READY
        }
    };

    abstract fun accept(input: String, coffeeMachine: CoffeeMachine)
}

fun String.toPositiveInt(): Int {
    val i = toIntOrNull() ?: 0
    return if (i < 0) 0 else i
}