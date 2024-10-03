package machine

fun main() {
    val ingredients = mutableMapOf(
        "water" to 400,
        "milk" to 540,
        "coffee" to 120,
        "cups" to 9,
        "money" to 550
    )

    while (action(ingredients) == true) {
    }
}

fun action(ingredients: MutableMap<String, Int>): Boolean {
    println("Write action (buy, fill, take, remaining, exit):")
    print("> ")
    val action = readln()

    when (action) {
        "buy" -> buy(ingredients)
        "fill" -> fill(ingredients)
        "take" -> take(ingredients)
        "remaining" -> state(ingredients)
        "exit" -> return false
        else -> println("Unknown Action")
    }
    return true
}

fun buy(ingredients: MutableMap<String, Int>) {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    print("> ")

    val input = readln()

    if (input == "back") {
        return
    }

    val coffeeType = input.toInt()
    if (coffeeType in 1..3) {
        makeCoffee(coffeeType, ingredients)
    }
}

fun makeCoffee(coffeeType: Int, ingredients: MutableMap<String, Int>) {
    var requiredWater = 0
    var requiredMilk = 0
    var requiredCoffee = 0
    var requiredMoney = 0
    var requiredCups = 0

    when (coffeeType) {
        1 -> {
            requiredCups = 1
            requiredWater = 250
            requiredCoffee = 16
            requiredMoney = 4
        }

        2 -> {
            requiredCups = 1
            requiredWater = 350
            requiredMilk = 75
            requiredCoffee = 20
            requiredMoney = 7
        }

        3 -> {
            requiredCups = 1
            requiredWater = 200
            requiredMilk = 100
            requiredCoffee = 12
            requiredMoney = 6
        }
    }

    // Verifica se há recursos suficientes
    val enoughWater = ingredients["water"]!! >= requiredWater
    val enoughMilk = ingredients["milk"]!! >= requiredMilk
    val enoughCoffee = ingredients["coffee"]!! >= requiredCoffee
    val enoughCups = ingredients["cups"]!! >= requiredCups

    if (enoughWater && enoughMilk && enoughCoffee && enoughCups) {
        ingredients["water"] = ingredients["water"]!! - requiredWater
        ingredients["milk"] = ingredients["milk"]!! - requiredMilk
        ingredients["coffee"] = ingredients["coffee"]!! - requiredCoffee
        ingredients["cups"] = ingredients["cups"]!! - requiredCups
        ingredients["money"] = ingredients["money"]!! + requiredMoney
    }
}

fun state(ingredients: MutableMap<String, Int>) {

    println("\nThe coffee machine has:")
    println("${ingredients["water"]} ml of water")
    println("${ingredients["milk"]} ml of milk")
    println("${ingredients["coffee"]} g of coffee beans")
    println("${ingredients["cups"]} disposable cups")
    println("${ingredients["money"]} of money\n")

}

fun fill(ingredients: MutableMap<String, Int>) {

    println("Write how many ml of water you want to add:")
    ingredients["water"] = ingredients["water"]!! + readln().toInt()
    println("Write how many ml of milk you want to add:")
    ingredients["milk"] = ingredients["milk"]!! + readln().toInt()
    println("Write how many grams of coffee beans you want to add:")
    ingredients["coffee"] = ingredients["coffee"]!! + readln().toInt()
    println("Write how many disposable cups you want to add:")
    ingredients["cups"] = ingredients["cups"]!! + readln().toInt()

}

fun take(ingredients: MutableMap<String, Int>) {

    println("I gave you $${ingredients["money"]}")
    ingredients["money"] = 0

}

