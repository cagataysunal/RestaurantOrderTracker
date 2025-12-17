package com.cagataysunal.restaurantordertracker.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cagataysunal.restaurantordertracker.data.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SessionProvider(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        private val RESTAURANT_ID_KEY = intPreferencesKey("restaurant_id")
    }

    val authToken: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }

    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    val user: Flow<User?> = context.dataStore.data.map { preferences ->
        val name = preferences[USER_NAME_KEY]
        val email = preferences[USER_EMAIL_KEY]
        val restaurantId = preferences[RESTAURANT_ID_KEY]
        if (name != null && email != null && restaurantId != null) {
            User(name, email, restaurantId)
        } else {
            null
        }
    }

    suspend fun saveUser(user: User) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = user.name
            preferences[USER_EMAIL_KEY] = user.email
            preferences[RESTAURANT_ID_KEY] = user.restaurantId
        }
    }

}
