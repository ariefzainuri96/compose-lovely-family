package lovely.family.android.Utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import lovely.family.android.Model.FormErrorModel
import kotlin.reflect.full.memberProperties

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

fun convertCamelToTitle(camelCase: String): String {
    // Use a regular expression to find the capital letters and insert spaces before them
    val titleCase = camelCase.replace(Regex("([a-z])([A-Z])"), "$1 $2")
    // Capitalize the first letter of each word
    return titleCase.split(" ").joinToString(" ") { it.replaceFirstChar { it.uppercase() } }
}

// Extension function to check all properties of any data class
inline fun <reified T : Any> T.checkAllProperties(): List<FormErrorModel> {
    val errors = mutableListOf<FormErrorModel>()

    for (property in T::class.memberProperties) {
        val value = property.get(this)
        if (value is String? && value.isNullOrEmpty()) {
            errors.add(FormErrorModel(key = property.name, error =
            "${convertCamelToTitle(property.name)} tidak boleh kosong"))
        }
    }

    return errors
}