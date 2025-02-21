package com.example.mydemo.cart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mydemo.cart.composables.DateTimePickerDialog
import com.example.mydemo.common.composables.BackButton
import com.example.mydemo.common.composables.PayButton
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun DateTimePickerScreen(navController: NavController) {
    var showPicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }
    var selectedPaymentOption by remember { mutableStateOf<String?>(null) }

    val paymentOptions = listOf(
        "Betalen in de winkel",
        "Betaal met creditcard",
        "Betaal met PayPal",
        "Betaal met iDEAL"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BackButton(onBack = { navController.popBackStack() })
                PayButton(
                    onClick = {
                        if (selectedDate != null && selectedTime != null && selectedPaymentOption != null) {
                            navController.navigate("confirmationScreen")
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(onClick = { showPicker = true }) {
                    Text("Selecteer Datum & Tijd")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                selectedDate?.let {
                    Text("Geselecteerde datum: ${it.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))}")
                }
                selectedTime?.let {
                    Text("Geselecteerde tijd: ${it.format(DateTimeFormatter.ofPattern("HH:mm"))}")
                }
            }
            if (showPicker) {
                DateTimePickerDialog(
                    onDateTimeSelected = { date, time ->
                        selectedDate = date
                        selectedTime = time
                        showPicker = false
                    },
                    onDismiss = { showPicker = false }
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            if (selectedDate != null && selectedTime != null) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Kies een betaalmethode:", modifier = Modifier.padding(bottom = 8.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    paymentOptions.forEach { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = selectedPaymentOption == option,
                                onClick = {
                                    if (option == "Betalen in de winkel") {
                                        selectedPaymentOption = option
                                    }
                                },
                                enabled = option == "Betalen in de winkel"
                            )
                            Text(
                                text = option,
                                modifier = Modifier.padding(start = 8.dp),
                                color = if (option == "Betalen in de winkel")
                                    MaterialTheme.colorScheme.onSurface
                                else
                                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }
                    }
                }
            }
        }
    }
}