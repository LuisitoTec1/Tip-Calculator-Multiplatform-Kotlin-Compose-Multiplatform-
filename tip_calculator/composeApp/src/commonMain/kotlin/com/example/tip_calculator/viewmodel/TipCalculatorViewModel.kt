package com.example.tip_calculator.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.ceil

data class TipUiState(//VALORES POR DEFECTO
    val billAmount: String = "",
    val tipPercentage: String = "",
    val roundUp: Boolean = false,
    val tipAmount: String = "0.00"
)

class TipCalculatorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TipUiState())
    val uiState: StateFlow<TipUiState> = _uiState.asStateFlow()

    fun onBillAmountChange(value: String) {
        _uiState.update { it.copy(billAmount = value) }
        recalculateTip()
    }

    fun onTipPercentageChange(value: String) {
        _uiState.update { it.copy(tipPercentage = value) }
        recalculateTip()
    }

    fun onRoundUpChange(enabled: Boolean) {
        _uiState.update { it.copy(roundUp = enabled) }
        recalculateTip()
    }

    private fun recalculateTip() {
        val amount = _uiState.value.billAmount.toDoubleOrNull() ?: 0.0
        val percentage = _uiState.value.tipPercentage.toDoubleOrNull() ?: 0.0

        var tip = amount * (percentage / 100)

        if (_uiState.value.roundUp) {
            tip = ceil(tip)
        }

        // Formateo básico a dos decimales
        val formattedTip = ((tip * 100.0).toLong() / 100.0).toString()
        _uiState.update { it.copy(tipAmount = formattedTip) }
    }

    override fun onCleared() {
        super.onCleared()
        // Criterio de aceptación: Demostrar comprensión del fin del ciclo de vida
        println("ViewModel: onCleared() ejecutado. Liberando recursos...")
    }
}
