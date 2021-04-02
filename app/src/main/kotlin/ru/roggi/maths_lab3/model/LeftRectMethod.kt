package ru.roggi.maths_lab3.model

import ru.roggi.comp.math.model.Equation

class LeftRectMethod(
        equation: Equation,
        leftBound: Double,
        rightBound: Double,
        n: Int
) : RectMethod(equation, leftBound, rightBound, n) {
    override fun getXi(i: Int, h: Double): Double = leftBound + h * (i - 1)
}