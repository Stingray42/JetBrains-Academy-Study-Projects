package coffee.machine

enum class Coffee(val price: Int, val requirements: Consumables) {
    ESPRESSO(4, Consumables(250, 0, 16, 1)),
    LATTE(7, Consumables(350, 75, 20, 1)),
    CAPPUCCINO(6, Consumables(200, 100, 12, 1));
}