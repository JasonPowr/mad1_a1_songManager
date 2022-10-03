package songmanager.console.helpers

const val red = "\u001b[31m"
const val reset = "\u001b[0m"
var errMessage = red + "Please enter a Valid Value"

fun validateString(str: String): Boolean {
    if (str.isBlank()) {
        println("$errMessage$reset")
        return false
    }
    return true
}

fun validateDouble(str: String): Boolean {
    if (str.toDoubleOrNull() == null) {
        println("$errMessage i.e Double(x.xx)$reset")
        return false
    }
    return true
}

fun validateInt(str: String): Boolean {
    if (str.toIntOrNull() == null) {
        println("$errMessage i.e Int(xxxx) $reset")
        return false
    }
    return true
}

fun validateBool(str: String): Boolean {
    if (str.toBooleanStrictOrNull() == null) {
        println("$errMessage i.e Boolean(true,false) $reset")
        return false
    }
    return true
}
fun handleMisInput(input: String): String {
    var str = ""
    str = input
    if (input.lowercase() == "yes" || input.lowercase() == "y" || input.lowercase() == "yea" || input.lowercase() == "t") {
        str = true.toString()
    } else if (input.lowercase() == "no" || input.lowercase() == "n" || input.lowercase() == "nah" || input.lowercase() == "f") {
        str = false.toString()
    }
    return str
}

 fun isTimeValid(input: String): Boolean {
     var str = ""
     str = input
     val min = str.split(".")[1].toInt()
     if(min > 60) {
         println("$errMessage i.e Seconds cannot be Over 60 $reset")
         return false
     }
     return true
 }


