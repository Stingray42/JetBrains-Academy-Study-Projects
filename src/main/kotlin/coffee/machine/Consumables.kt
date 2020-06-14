package coffee.machine

class Consumables(var water: Int,
                  var milk: Int,
                  var beans: Int,
                  var cups: Int) {

    operator fun plus(other: Consumables): Consumables {
        return Consumables(
                this.water + other.water,
                this.milk + other.milk,
                this.beans + other.beans,
                this.cups + other.cups
        )
    }

    operator fun minus(other: Consumables): Consumables {
        return Consumables(
                this.water - other.water,
                this.milk - other.milk,
                this.beans - other.beans,
                this.cups - other.cups
        )
    }
}