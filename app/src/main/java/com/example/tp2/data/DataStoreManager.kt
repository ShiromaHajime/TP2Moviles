package com.example.tp2.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {

    private val MAX_SCORE_KEY = intPreferencesKey("max_score")

    val maxScoreFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[MAX_SCORE_KEY] ?: 0
        }

    suspend fun updateMaxScore(newScore: Int) {
        context.dataStore.edit { preferences ->
            preferences[MAX_SCORE_KEY] = newScore
        }
    }
}
