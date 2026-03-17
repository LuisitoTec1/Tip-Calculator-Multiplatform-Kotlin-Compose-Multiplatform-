package com.example.tip_calculator.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tip_calculator.viewmodel.TipCalculatorViewModel

@Composable
fun TipCalculatorScreen(
    viewModel: TipCalculatorViewModel = viewModel { TipCalculatorViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    // Demostración del ciclo de vida de la Composición
    DisposableEffect(Unit) {
        println("UI: Entrando a la composicion de TipCalculatorScreen")
        onDispose {
            println("UI: Saliendo de la composicion de TipCalculatorScreen")
        }
    }

    // Monto de la cuenta
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = uiState.billAmount,
            onValueChange = { viewModel.onBillAmountChange(it) },
            label = { Text("Monto de la cuenta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Porcentaje de propina
        TextField(
            value = uiState.tipPercentage,
            onValueChange = { viewModel.onTipPercentageChange(it) },
            label = { Text("Porcentaje de propina (%)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Opción de redondear
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Text("Redondear propina")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = uiState.roundUp,
                onCheckedChange = { viewModel.onRoundUpChange(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Resultado
        Text(
            text = "Propina: $${uiState.tipAmount}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}