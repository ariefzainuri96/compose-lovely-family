package lovely.family.android.Pages.Login

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lovely.family.android.Features.Login.LoginRepository
import lovely.family.android.Features.Login.Model.LoginFormModel
import lovely.family.android.Model.FormErrorModel
import lovely.family.android.Utils.RequestState
import lovely.family.android.Utils.UserPreferences
import lovely.family.android.Utils.checkAllProperties
import lovely.family.android.Utils.dataStore
import lovely.family.android.Utils.saveLoginStatus
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val application: Application,
    private val repository: LoginRepository
): ViewModel() {
    var loginForm = MutableStateFlow(LoginFormModel()); private set
    var loginFormError = MutableStateFlow(listOf<FormErrorModel>()); private set
    var loginState = MutableStateFlow(RequestState.IDLE); private set

    fun updateLoginForm(update: LoginFormModel.() -> LoginFormModel) {
        loginForm.update { it.update() }
    }

    fun login() {
        loginFormError.value = loginForm.value.checkAllProperties()

        if (loginFormError.value.isNotEmpty()) {
            return
        }

        val handler = CoroutineExceptionHandler {_, throwable ->
            println("Error happening: $throwable")

            loginState.value = RequestState.ERROR
        }

        loginState.value = RequestState.LOADING

        viewModelScope.launch(handler) {
            val response = async { repository.login(loginForm.value) }.await()

            loginState.value = if (response.isSuccessful) RequestState.SUCCESS else RequestState.ERROR

            if (response.isSuccessful) {
                saveLoginStatus(application.applicationContext, true)

                application.applicationContext.dataStore.edit { preferences ->
                    preferences[UserPreferences.TOKEN] = response.body()?.data ?: ""
                }
            }
        }
    }
}