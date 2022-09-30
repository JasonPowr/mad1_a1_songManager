package songmanager.console.helpers

const val red = "\u001b[31m"
const val reset = "\u001b[0m"
var errMessage = red+"Please enter a Valid Value"

fun validateString(str: String): Boolean{
    if (str.isBlank()) {
        println(errMessage)
        return false
    }
    return true
}

fun validateDouble(str: String): Boolean {
    if (str.toDoubleOrNull() == null){
        println("$errMessage i.e Double(x.xx)$reset")
        return false
    }
     return true
}

fun validateInt(str: String): Boolean {
    if (str.toIntOrNull() == null){
        println("$errMessage i.e Int(xxxx) $reset")
        return false
    }
    return true
}

fun validateBool(str: String): Boolean {
    if (str.toBooleanStrictOrNull() == null){
        println("$errMessage i.e Boolean(true,false) $reset")
        return false
    }
    return true
}

