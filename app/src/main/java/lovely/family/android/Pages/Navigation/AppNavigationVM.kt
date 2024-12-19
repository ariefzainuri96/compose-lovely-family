package lovely.family.android.Pages.Navigation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import lovely.family.android.Utils.getLoginStatus
import javax.inject.Inject

@HiltViewModel
class AppNavigationVM @Inject constructor(
    application: Application
): ViewModel() {
    private val _isLoggedIn = getLoginStatus(application.applicationContext).stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        false
    )

    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
}