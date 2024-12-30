package lovely.family.android.Utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_prefs")

object UserPreferences {
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    val TOKEN = stringPreferencesKey("token")
}