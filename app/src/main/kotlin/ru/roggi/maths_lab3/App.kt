package ru.roggi.maths_lab3

import ru.roggi.comp.math.EasyCompMath
import ru.roggi.comp.math.utils.RouterBuilder
import ru.roggi.comp.math.view.InputTwoScene
import ru.roggi.console.application.view.scene.ChooseScene
import ru.roggi.console.application.view.scene.Router
import ru.roggi.maths_lab3.model.*
import ru.roggi.maths_lab3.view.MethodScene
import tornadofx.launch

const val CHOOSE_METHOD_ROUTE = "chooseRoute"
const val INPUT_METHOD_BOUNDS_ROUTE = "inputMethodBounds"
const val LEFT_RECTANGLES_METHOD_ROUTE = "leftRectMethod"
const val RIGHT_RECTANGLES_METHOD_ROUTE = "rightRectMethod"
const val MIDDLE_RECTANGLES_METHOD_ROUTE = "middleRectMethod"
const val TRAPEZES_METHOD_ROUTE = "trapezesMethod"
const val SIMPSON_METHOD_ROUTE = "simpsonMethod"

class RouterBuilderImpl: RouterBuilder {
    override fun build(): Router =
            Router().apply {
                register(CHOOSE_METHOD_ROUTE, ChooseScene(
                        "Choose method:\nLeft rectangles - 1\nRight rectangles - 2\nMiddle rectangles - 3\nTrapezes - 4\nSimpson = 5",
                        "",
                        listOf("1", "2", "3", "4", "5")) {
                            when(it) {
                                "1" -> LEFT_RECTANGLES_METHOD_ROUTE
                                "2" -> RIGHT_RECTANGLES_METHOD_ROUTE
                                "3" -> MIDDLE_RECTANGLES_METHOD_ROUTE
                                "4" -> TRAPEZES_METHOD_ROUTE
                                else -> SIMPSON_METHOD_ROUTE
                            }
                        }
                )
                register(INPUT_METHOD_BOUNDS_ROUTE, InputTwoScene(
                        "Enter bounds for method between -50 and 50:",
                        String::toDouble,
                        String::toDouble,
                        {
                            left, right -> left in -50.0..50.0 &&
                                           right in -50.0..50.0 &&
                                           left < right
                        }
                ))
                register(LEFT_RECTANGLES_METHOD_ROUTE, MethodScene(LeftRectMethod()))
                register(RIGHT_RECTANGLES_METHOD_ROUTE, MethodScene(RightRectMethod()))
                register(MIDDLE_RECTANGLES_METHOD_ROUTE, MethodScene(MiddleRectMethod()))
                register(TRAPEZES_METHOD_ROUTE, MethodScene(TrapezesMethod()))
                register(SIMPSON_METHOD_ROUTE, MethodScene(SimpsonMethod()))
            }
}

fun main() {
    launch<EasyCompMath>(CHOOSE_METHOD_ROUTE, "ru.roggi.maths_lab3")
}
