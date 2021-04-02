package ru.roggi.maths_lab3.model

import ru.roggi.comp.math.model.Equation

abstract class RectMethod(
        equation: Equation,
        leftBound: Double,
        rightBound: Double,
        n: Int
) : Method(equation, leftBound, rightBound, n) {
    override fun evaluate(): Double {
        val h = (rightBound - leftBound) / n

        var result = 0.0

        for (i in 1..n) {
            result += h * equation.evaluate(getXi(i, h))
        }

        return result
    }

    abstract fun getXi(i: Int, h: Double): Double
}