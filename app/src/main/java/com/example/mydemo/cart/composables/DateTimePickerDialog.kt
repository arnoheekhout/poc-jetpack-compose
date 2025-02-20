package com.example.mydemo.cart.composables

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar

@Composable
fun DateTimePickerDialog(
    onDateTimeSelected: (LocalDate, LocalTime) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(true) }
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    if (showDatePicker) {
        ShowDatePicker(
            context = context,
            onDateSelected = { date ->
                selectedDate = date
                showDatePicker = false
                showTimePicker = true
            },
            onDismiss = { onDismiss() }
        )
    }

    if (showTimePicker) {
        ShowTimePicker(
            context = context,
            onTimeSelected = { time ->
                selectedDate?.let { date ->
                    onDateTimeSelected(date, time)
                }
                onDismiss()
            },
            onDismiss = { onDismiss() }
        )
    }
}

fun ShowDatePicker(
    context: Context,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val calendar = Calendar.getInstance()
    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            onDateSelected(selectedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

fun ShowTimePicker(
    context: Context,
    onTimeSelected: (LocalTime) -> Unit,
    onDismiss: () -> Unit
) {
    val calendar = Calendar.getInstance()
    TimePickerDialog(
        context,
        { _, hour, minute ->
            onTimeSelected(LocalTime.of(hour, minute))
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    ).show()
}