package com.example.mydemo.cart.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun DatePickerDialog(
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val picker = remember {
        MaterialDatePicker.Builder.datePicker().build()
    }

    DisposableEffect(picker) {
        picker.addOnPositiveButtonClickListener { selection ->
            val selectedDate = Instant.ofEpochMilli(selection)
                .atZone(ZoneId.systemDefault()).toLocalDate()
            onDateSelected(selectedDate)
        }
        picker.show((context as androidx.fragment.app.FragmentActivity).supportFragmentManager, "DATE_PICKER")
        onDispose { }
    }
}