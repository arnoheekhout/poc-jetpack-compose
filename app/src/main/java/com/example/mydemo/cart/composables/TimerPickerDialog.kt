package com.example.mydemo.cart.composables

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TimePicker
import androidx.compose.runtime.*
import java.time.LocalTime
import java.util.Calendar

@Composable
fun TimePickerDialog(
    context: Context,
    onTimeSelected: (LocalTime) -> Unit,
    onDismiss: () -> Unit
) {
    val calendar = Calendar.getInstance()

    LaunchedEffect(Unit) {
        TimePickerDialog(
            context,
            { _: TimePicker, hour: Int, minute: Int ->
                onTimeSelected(LocalTime.of(hour, minute))
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }
}