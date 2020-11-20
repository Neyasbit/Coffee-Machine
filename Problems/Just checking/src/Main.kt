import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    //val map = mutableMapOf(1 to "Java", 2 to "Kotlin", 3 to "Scala", 4 to "Python")
    //print(if (map.contains(scanner.nextInt())) "Yes!" else "Unknown number")
    val num = scanner.nextInt()
    when(num){
        1,3,4 -> print("No!")
        2 -> print("Yes!")
        else -> print("Unknown number")
    }
}