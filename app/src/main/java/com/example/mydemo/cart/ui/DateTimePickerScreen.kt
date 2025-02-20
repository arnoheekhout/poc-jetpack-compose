package com.example.mydemo.cart.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mydemo.cart.composables.DatePickerDialog
import com.example.mydemo.cart.composables.DateTimeViewModel
import com.example.mydemo.cart.composables.TimePickerDialog

@Composable
fun DateTimePickerScreen(viewModel: DateTimeViewModel = viewModel()) {
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { showDatePicker = true }) {
            Text("Selecteer Datum")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { showTimePicker = true }) {
            Text("Selecteer Tijd")
        }

        viewModel.selectedDate.value?.let {
            Text("Geselecteerde datum: $it")
        }
        viewModel.selectedTime.value?.let {
            Text("Geselecteerde tijd: $it")
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDateSelected = { viewModel.setDate(it); showDatePicker = false },
                onDismiss = { showDatePicker = false }
            )
        }

        if (showTimePicker) {
            TimePickerDialog(
                context = context,
                onTimeSelected = { viewModel.setTime(it); showTimePicker = false },
                onDismiss = { showTimePicker = false }
            )
        }
    }
}