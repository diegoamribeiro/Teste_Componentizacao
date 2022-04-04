package com.example.testecomponentizacao.data.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.testecomponentizacao.data.preferences.UserPreferencesRepository.UsersPreferencesKeys.PASSWORD_KEY
import com.example.testecomponentizacao.data.preferences.UserPreferencesRepository.UsersPreferencesKeys.USERNAME_KEY
import com.example.testecomponentizacao.utils.Constants.USER_PREFERENCES
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

private val Context.dataStorePreferences: DataStore<Preferences> by preferencesDataStore(
    USER_PREFERENCES
)

class UserPreferencesRepository @Inject constructor(@ApplicationContext context: Context) {

    private val userDataStore: DataStore<Preferences> = context.dataStorePreferences

    private object UsersPreferencesKeys {
        val USERNAME_KEY = stringPreferencesKey("USERNAME")
        val PASSWORD_KEY = stringPreferencesKey("PASSWORD")
    }

    suspend fun saveUserData(username: String, password: String) {
        userDataStore.edit { data ->
            data[USERNAME_KEY] = username
            data[PASSWORD_KEY] = password
        }
    }

    suspend fun deleteAllData() {
        userDataStore.edit { data ->
            data.clear()
        }
    }

    val userNameFlow: Flow<String> = userDataStore.data
        .catch {
            if (this is Exception) {
                emit(emptyPreferences())
            }
        }.map { username ->
            username[USERNAME_KEY] ?: ""
        }

    val passwordFlow: Flow<String> = userDataStore.data
        .catch {
            if (this is Exception) {
                emit(emptyPreferences())
            }
        }.map { password ->
            password[PASSWORD_KEY] ?: ""
        }

}
