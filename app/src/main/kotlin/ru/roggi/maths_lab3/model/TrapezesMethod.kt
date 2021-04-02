package ru.roggi.maths_lab3.model

import ru.roggi.comp.math.model.Equation

class TrapezesMethod(
        equation: Equation,
        leftBound: Double,
        rightBound: Double,
        n: Int
) : Method(equation, leftBound, rightBound, n) {
    override fun evaluate(): Double {
        val h = (rightBound - leftBound) / n

        var result = (equation.evaluate(leftBound) + equation.evaluate(leftBound + h * n)) / 2

        for (i in 1 until n) {
            result += equation.evaluate(leftBound + h * i)
        }

        return result * h
    }
}