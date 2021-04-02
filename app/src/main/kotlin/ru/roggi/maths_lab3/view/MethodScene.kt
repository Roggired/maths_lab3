package ru.roggi.maths_lab3.view

import ru.roggi.comp.math.model.Equation
import ru.roggi.comp.math.model.emptyEquation
import ru.roggi.comp.math.view.InputIntent
import ru.roggi.comp.math.view.InputTwoIntent
import ru.roggi.console.application.model.State
import ru.roggi.console.application.view.scene.SceneContext
import ru.roggi.console.application.view.scene.StatefulScene
import ru.roggi.maths_lab3.INPUT_ACCURACY_ROUTE
import ru.roggi.maths_lab3.INPUT_EQUATION_ROUTE
import ru.roggi.maths_lab3.INPUT_METHOD_BOUNDS_ROUTE
import ru.roggi.maths_lab3.INPUT_N_ROUTE
import ru.roggi.maths_lab3.model.Method

class MethodState: State {
    var equation = emptyEquation()
    var accuracy = 0.01
    var leftBound = -5.0
    var rightBound = 5.0
    var n = 4
}

class MethodScene(private val method: Method): StatefulScene<MethodState>(MethodState()) {
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

        sceneContext.router.switch(INPUT_ACCURACY_ROUTE) {
            it as InputIntent<*>
            state.accuracy = it.value as Double
        }

        sceneContext.router.switch(INPUT_N_ROUTE) {
            it as InputIntent<*>
            state.n = it.value as Int
        }
    }
}