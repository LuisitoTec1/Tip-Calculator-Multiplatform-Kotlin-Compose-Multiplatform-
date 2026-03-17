package com.example.tip_calculator

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.tip_calculator.ui.TipCalculatorScreen

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Calculadora de Propinas") {
        TipCalculatorScreen()
    }
}