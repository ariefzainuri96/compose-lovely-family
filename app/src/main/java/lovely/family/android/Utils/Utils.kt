package lovely.family.android.Utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

suspend fun saveLoginStatus(context: Context, isLoggedIn: Boolean) {
    context.dataStore.edit { prefs ->
        prefs[UserPreferences.IS_LOGGED_IN] = isLoggedIn
    }
}

fun getLoginStatus(context: Context): Flow<Boolean> {
    return context.dataStore.data.map { prefs ->
        prefs[UserPreferences.IS_LOGGED_IN] == true
    }
}