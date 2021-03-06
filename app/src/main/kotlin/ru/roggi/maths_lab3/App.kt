package ru.roggi.maths_lab3

import ru.roggi.comp.math.EasyCompMath
import ru.roggi.comp.math.model.createEquationFrom
import ru.roggi.comp.math.utils.RouterBuilder
import ru.roggi.comp.math.view.InputScene
import ru.roggi.comp.math.view.InputTwoScene
import ru.roggi.comp.math.view.YesNoScene
import ru.roggi.console.application.view.scene.ChooseScene
import ru.roggi.console.application.view.scene.Router
import ru.roggi.maths_lab3.model.*
import ru.roggi.maths_lab3.view.MethodScene
import tornadofx.launch

const val CHOOSE_METHOD_ROUTE = "chooseRoute"

const val INPUT_METHOD_BOUNDS_ROUTE = "inputMethodBounds"
const val INPUT_N_ROUTE = "inputN"
const val INPUT_ACCURACY_ROUTE = "inputAccuracy"
const val INPUT_EQUATION_ROUTE = "inputEquation"

const val LEFT_RECTANGLES_METHOD_ROUTE = "leftRectMethod"
const val RIGHT_RECTANGLES_METHOD_ROUTE = "rightRectMethod"
const val MIDDLE_RECTANGLES_METHOD_ROUTE = "middleRectMethod"
const val TRAPEZES_METHOD_ROUTE = "trapezesMethod"
const val SIMPSON_METHOD_ROUTE = "simpsonMethod"

class RouterBuilderImpl: RouterBuilder {
    override fun build(): Router =
            Router().apply {
                register(CHOOSE_METHOD_ROUTE, ChooseScene(
                        "Choose method:\nLeft rectangles - 1\nRight rectangles - 2\nMiddle rectangles - 3\nTrapezes - 4\nSimpson - 5",
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
                        "Enter bounds between -50 and 50:",
                        String::toDouble,
                        String::toDouble,
                        {
                            left, right -> left in -50.0..50.0 &&
                                           right in -50.0..50.0 &&
                                           left < right
                        }
                ))
                register(INPUT_N_ROUTE, InputScene(
                        "Enter N between 2 and 100:",
                        String::toInt,
                        {
                            it in 2..100
                        }
                ))
                register(INPUT_ACCURACY_ROUTE, InputScene(
                        "Enter accuracy between 0.001 and 1.000:",
                        String::toDouble,
                        {
                            it in 0.001..1.000
                        }
                ))
                register(INPUT_EQUATION_ROUTE, InputScene(
                        "Enter equation:",
                        ::createEquationFrom,
                        {
                            true
                        }
                ))
                register(LEFT_RECTANGLES_METHOD_ROUTE, MethodScene {
                    LeftRectMethod(it.equation, it.leftBound, it.rightBound, it.n)
                })
                register(RIGHT_RECTANGLES_METHOD_ROUTE, MethodScene {
                    RightRectMethod(it.equation, it.leftBound, it.rightBound, it.n)
                })
                register(MIDDLE_RECTANGLES_METHOD_ROUTE, MethodScene {
                    MiddleRectMethod(it.equation, it.leftBound, it.rightBound, it.n)
                })
                register(TRAPEZES_METHOD_ROUTE, MethodScene {
                    TrapezesMethod(it.equation, it.leftBound, it.rightBound, it.n)
                })
                register(SIMPSON_METHOD_ROUTE, MethodScene {
                    SimpsonMethod(it.equation, it.leftBound, it.rightBound, it.n)
                })
            }
}

fun main() {
    launch<EasyCompMath>(CHOOSE_METHOD_ROUTE, "ru.roggi.maths_lab3")
}
