package ru.roggi.maths_lab3.model

import ru.roggi.comp.math.model.Equation

class SimpsonMethod(
        equation: Equation,
        leftBound: Double,
        rightBound: Double,
        n: Int
) : Method(equation, leftBound, rightBound, n)  {
    override fun evaluate(): Double {
        val h = (rightBound - leftBound) / n

        var result = equation.evaluate(leftBound) + equation.evaluate(leftBound + h * n)

        for (i in 1 until n step 2) {
            result += 4 * equation.evaluate(leftBound + h * i)
        }

        for (i in 2 until n-1 step 2) {
            result += 2 * equation.evaluate(leftBound + h * i)
        }

        return h / 3 * result
    }
}