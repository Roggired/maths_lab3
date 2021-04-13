package ru.roggi.maths_lab3

fun getFormatBasedOnAccuracy(accuracy: Double): String {
    val accuracyDigitsAfterDot = accuracy.toString().split(".")[1]
    var symbolsAfterDot = 0
    var flag = false
    for (symbol in accuracyDigitsAfterDot) {
        symbolsAfterDot++
        if (symbol != '0') {
            flag = true
        }

        if (symbol == '0' && flag) {
            symbolsAfterDot--
            break
        }
    }

    return "%.${symbolsAfterDot}f"
}