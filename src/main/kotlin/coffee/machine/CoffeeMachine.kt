package coffee.machine

class CoffeeMachine(var supplies: Consumables, var money: Int) {
    var state = State.READY

    fun read(input: String) {
        state.accept(input, this)
    }

    fun make(coffee: Coffee) = when {
        supplies.water <= coffee.requirements.water -> {
            println("Sorry, not enough water!")
        }
        supplies.milk <= coffee.requirements.milk -> {
            println("Sorry, not enough milk!")
        }
        supplies.beans <= coffee.requirements.beans -> {
            println("Sorry, not enough coffee beans!")
        }
        supplies.cups <= coffee.requirements.cups -> {
            println("Sorry, not enough disposable cups!")
        }
        else -> {
            println("I have enough resources, making you a coffee!")
            supplies -= coffee.requirements
            money += coffee.price
        }
    }

    fun withdraw() {
        println("I gave you \$$money")
        money = 0
    }

    fun getState(): String {
        return "The coffee machine has:\n" +
                "${supplies.water} of water\n" +
                "${supplies.milk} of milk\n" +
                "${supplies.beans} of coffee beans\n" +
                "${supplies.cups} of disposable cups\n" +
                "$money of money"
    }
}