package ru.roggi.maths_lab3.model

import ru.roggi.comp.math.model.Equation

abstract class Method(
        protected val equation: Equation,
        protected val leftBound: Double,
        protected val rightBound: Double,
        protected val n: Int
) {
    abstract fun evaluate(): Double
}