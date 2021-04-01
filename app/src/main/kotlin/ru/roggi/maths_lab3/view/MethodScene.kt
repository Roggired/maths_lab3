package ru.roggi.maths_lab3.view

import ru.roggi.comp.math.model.emptyEquation
import ru.roggi.console.application.model.State
import ru.roggi.console.application.view.scene.SceneContext
import ru.roggi.console.application.view.scene.StatefulScene
import ru.roggi.maths_lab3.model.Method

class MethodState: State {
    var equation = emptyEquation()
    var accuracy = 0.01
    var leftBound = -5
    var rightBound = 5
}

class MethodScene(private val method: Method): StatefulScene<MethodState>(MethodState()) {
    override fun start(sceneContext: SceneContext) {

    }
}