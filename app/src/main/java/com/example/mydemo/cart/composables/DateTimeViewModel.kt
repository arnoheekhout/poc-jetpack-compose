package com.example.mydemo.cart.composables

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime

class DateTimeViewModel : ViewModel() {
    var selectedDate = mutableStateOf<LocalDate?>(null)
        private set

    var selectedTime = mutableStateOf<LocalTime?>(null)
        private set

    fun setDate(date: LocalDate) {
        selectedDate.value = date
    }

    fun setTime(time: LocalTime) {
        selectedTime.value = time
    }
}