package ru.roggi.maths_lab3.view

import ru.roggi.comp.math.YES_NO_ROUTE
import ru.roggi.comp.math.model.emptyEquation
import ru.roggi.comp.math.view.EquationIntent
import ru.roggi.comp.math.view.InputIntent
import ru.roggi.comp.math.view.InputTwoIntent
import ru.roggi.comp.math.view.YesNoIntent
import ru.roggi.comp.math.view.presenter.Presenter
import ru.roggi.console.application.model.State
import ru.roggi.console.application.view.scene.SceneContext
import ru.roggi.console.application.view.scene.StatefulScene
import ru.roggi.maths_lab3.*
import ru.roggi.maths_lab3.model.Method
import kotlin.system.exitProcess

class MethodState: State {
    var equation = emptyEquation()
    var leftBound = -5.0
    var rightBound = 5.0
    var n = 4
    var accuracy: Double? = null
}

class MethodScene(private val methodProducer: (MethodState) -> Method): StatefulScene<MethodState>(MethodState()) {
    override fun start(sceneContext: SceneContext) {
        sceneContext.router.switch(INPUT_EQUATION_ROUTE) {
            it as EquationIntent
            state.equation = it.equation
        }

        sceneContext.router.switch(INPUT_METHOD_BOUNDS_ROUTE) {
            it as InputTwoIntent<*, *>
            state.leftBound = it.a as Double
            state.rightBound = it.b as Double
        }

        var flag = true
        sceneContext.put("greetings", "Do you want to input N? (Y - input N, N - input accuracy)")
        sceneContext.router.switch(YES_NO_ROUTE) {
            it as YesNoIntent
            flag = it.isYes
        }

        if (flag) {
            sceneContext.router.switch(INPUT_N_ROUTE) {
                it as InputIntent<*>
                state.n = it.value as Int
            }
        } else {
            sceneContext.router.switch(INPUT_ACCURACY_ROUTE) {
                it as InputIntent<*>
                state.accuracy = it.value as Double
            }
        }

        println("Equation: ${Presenter.present(state.equation)}")
        println("Bounds: ${presentBounds()}")

        if (flag) {
            println("N: ${state.n}")
        } else {
            println("Accuracy: ${state.accuracy}")
        }

        val method = methodProducer(state)

        if (flag) {
            println("Value of the integral: ${presentValue(0.01, method.evaluate())}")
        } else {
            println("Value of the integral with accuracy ${state.accuracy}: ${presentValue(state.accuracy!!, method.evaluateWithAccuracy(state.accuracy!!))}")
        }

        var answer = true
        sceneContext.put("greetings", "Do you want to continue?")
        sceneContext.router.switch(YES_NO_ROUTE) {
            it as YesNoIntent
            answer = it.isYes
        }

        if (answer) {
            sceneContext.router.switch(CHOOSE_METHOD_ROUTE)
        } else {
            exitProcess(0);
        }
    }

    private fun presentBounds(): String = "[%.2f;%.2f]".format(state.leftBound, state.rightBound)

    private fun presentValue(accuracy: Double, value: Double): String {
        return getFormatBasedOnAccuracy(accuracy).format(value)
    }
}