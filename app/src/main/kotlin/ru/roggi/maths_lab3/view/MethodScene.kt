package ru.roggi.maths_lab3.view

import ru.roggi.comp.math.model.Equation
import ru.roggi.comp.math.model.emptyEquation
import ru.roggi.comp.math.view.InputIntent
import ru.roggi.comp.math.view.InputTwoIntent
import ru.roggi.comp.math.view.presenter.Presenter
import ru.roggi.console.application.model.State
import ru.roggi.console.application.view.scene.SceneContext
import ru.roggi.console.application.view.scene.StatefulScene
import ru.roggi.maths_lab3.INPUT_EQUATION_ROUTE
import ru.roggi.maths_lab3.INPUT_METHOD_BOUNDS_ROUTE
import ru.roggi.maths_lab3.INPUT_N_ROUTE
import ru.roggi.maths_lab3.model.Method

class MethodState: State {
    var equation = emptyEquation()
    var leftBound = -5.0
    var rightBound = 5.0
    var n = 4
}

class MethodScene(private val methodProducer: (MethodState) -> Method): StatefulScene<MethodState>(MethodState()) {
    override fun start(sceneContext: SceneContext) {
        sceneContext.router.switch(INPUT_EQUATION_ROUTE) {
            it as InputIntent<*>
            state.equation = it.value as Equation
        }

        sceneContext.router.switch(INPUT_METHOD_BOUNDS_ROUTE) {
            it as InputTwoIntent<*, *>
            state.leftBound = it.a as Double
            state.rightBound = it.b as Double
        }

        sceneContext.router.switch(INPUT_N_ROUTE) {
            it as InputIntent<*>
            state.n = it.value as Int
        }

        println("Equation: ${Presenter.present(state.equation)}")
        println("Bounds: ${presentBounds()}")
        println("N: ${state.n}")

        val method = methodProducer(state)

        println("Value of the integral: ${method.evaluate()}")
    }

    private fun presentBounds(): String = "[%.2f;%.2f]".format(state.leftBound, state.rightBound)
}