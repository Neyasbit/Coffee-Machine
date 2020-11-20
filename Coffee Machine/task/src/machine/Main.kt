package machine

import java.util.*

val scanner = Scanner(System.`in`)

fun main() {
    val coffeeMachine = CoffeeMachine()
    while (true) {
        print("Write action (buy, fill, take, remaining, exit): ")
        when (val a = scanner.next()) {
            "exit" -> break
            else -> coffeeMachine.startMachine(a)
        }
    }
}

class CoffeeMachine {
    var money : Int = 550
    var water : Int = 400
    var milk : Int = 540
    var beans : Int = 120
    var cups = 9


    fun startMachine(action : String){
        when(action){
            "buy" -> buyCoffee()
            "fill" -> fillMachine()
            "take" -> getMoney()
            "remaining" -> info()
        }
    }
    private fun buyCoffee() {
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        val type = scanner.next()
        if (type == "back") return

        val coffeeType = Coffee.find(type.toInt())
        val notEnoughRes = getEnoughRes(coffeeType)
        if (notEnoughRes == null) {
            money+=coffeeType.money
            water-=coffeeType.water
            milk-=coffeeType.milk
            beans-=coffeeType.beans
            cups-=coffeeType.cup
            println("I have enough resources, making you a coffee!")
        } else {
        println("Sorry, not enough $notEnoughRes!")
        }
    }
    private fun getEnoughRes(coffeeType: Coffee) : String? {
        if (water < coffeeType.water) return "water"
        if (milk < coffeeType.milk) return "milk"
        if (beans < coffeeType.beans) return "coffee beans"
        if (cups < 1) return "disposable cups"
        return null
    }
    private fun fillMachine(){
        print("Write how many ml of water do you want to add: > ")
        water+= readLine()!!.toInt()
        print("Write how many ml of milk do you want to add: > ")
        milk+= readLine()!!.toInt()
        print("Write how many grams of coffee beans do you want to add: > ")
        beans+= readLine()!!.toInt()
        print("Write how many disposable cups of coffee do you want to add: > ")
        cups+= readLine()!!.toInt()

    }

    private fun getMoney(){
        print("I gave you \$$money")
        println("\n")
        money = 0
    }

    private fun info() {
        print("\nThe coffee machine has:\n" +
                "$water of water\n" +
                "$milk of milk\n" +
                "$beans of coffee beans\n" +
                "$cups of disposable cups\n" +
                "$$money of money")
        println("\n")
    }
}

enum class Coffee(val type: String, val water: Int,val milk: Int = 0, val beans: Int,val money: Int, val cup: Int) {
    Espresso("Espresso", 250, beans = 16, money = 4, cup = 1),
    Latte("Latte", 350,75,20,7, 1),
    Cappuccino("Cappuccino", 200,100,12,6, 1);

    companion object {
            fun find(num: Int)  = values()[num-1]
    }
}
