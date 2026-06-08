package com.example.deliveryworthiness.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {

    companion object {
        val GAS_PRICE_KEY = floatPreferencesKey("gas_price")
        val AVERAGE_CONSUMPTION_KEY = floatPreferencesKey("average_consumption")
    }

    val gasPrice: Flow<Float> = context.dataStore.data.map { preferences ->
        preferences[GAS_PRICE_KEY] ?: 0.0f
    }

    val averageConsumption: Flow<Float> = context.dataStore.data.map { preferences ->
        preferences[AVERAGE_CONSUMPTION_KEY] ?: 1.0f // Default to 1 to avoid division by zero
    }

    suspend fun saveSettings(gasPrice: Float, averageConsumption: Float) {
        context.dataStore.edit { preferences ->
            preferences[GAS_PRICE_KEY] = gasPrice
            preferences[AVERAGE_CONSUMPTION_KEY] = averageConsumption
        }
    }
}
