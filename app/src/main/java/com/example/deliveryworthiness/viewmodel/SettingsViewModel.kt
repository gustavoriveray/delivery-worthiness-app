package com.example.deliveryworthiness.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryworthiness.data.SettingsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {

    var gasPrice by mutableStateOf("")
        private set

    var averageConsumption by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            val currentGasPrice = repository.gasPrice.first()
            val currentAverageConsumption = repository.averageConsumption.first()
            gasPrice = currentGasPrice.toString()
            averageConsumption = currentAverageConsumption.toString()
        }
    }

    fun updateGasPrice(value: String) {
        gasPrice = value
    }

    fun updateAverageConsumption(value: String) {
        averageConsumption = value
    }

    fun saveSettings(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val gp = gasPrice.toFloatOrNull() ?: 0.0f
            val ac = averageConsumption.toFloatOrNull() ?: 1.0f
            repository.saveSettings(gp, ac)
            onSuccess()
        }
    }
}
