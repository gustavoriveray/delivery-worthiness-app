package com.example.deliveryworthiness.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryworthiness.data.SettingsRepository
import kotlinx.coroutines.launch

class HomeViewModel(repository: SettingsRepository) : ViewModel() {

    private var gasPriceValue by mutableStateOf(0.0f)
    private var averageConsumptionValue by mutableStateOf(1.0f)

    init {
        viewModelScope.launch {
            repository.gasPrice.collect {
                gasPriceValue = it
            }
        }
        viewModelScope.launch {
            repository.averageConsumption.collect {
                averageConsumptionValue = it
            }
        }
    }

    var milesEstimated by mutableStateOf("")
        private set

    var jobPay by mutableStateOf("")
        private set

    val jobCost by derivedStateOf {
        val miles = milesEstimated.toFloatOrNull() ?: 0.0f
        if (averageConsumptionValue > 0) {
            (gasPriceValue * miles) / averageConsumptionValue
        } else {
            0.0f
        }
    }

    val profit by derivedStateOf {
        val pay = jobPay.toFloatOrNull() ?: 0.0f
        pay - jobCost
    }

    fun updateMilesEstimated(value: String) {
        milesEstimated = value
    }

    fun updateJobPay(value: String) {
        jobPay = value
    }
}
