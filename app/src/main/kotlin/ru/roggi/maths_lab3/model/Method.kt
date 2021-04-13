package ru.roggi.maths_lab3.model

import ru.roggi.comp.math.model.Equation
import kotlin.math.abs

abstract class Method(
        protected val equation: Equation,
        protected val leftBound: Double,
        protected val rightBound: Double,
        protected var n: Int
) {
    abstract fun evaluate(): Double

    fun evaluateWithAccuracy(accuracy: Double): Double {
        n = 5
        var I0 = evaluate()
        n *= 2
        var I1 = evaluate()

        while(abs(I1 - I0) >= accuracy) {
            I0 = I1;
            n *= 2;
            I1 = evaluate();
        }

        return I1;
    }
}