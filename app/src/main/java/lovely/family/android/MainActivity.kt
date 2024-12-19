package lovely.family.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import lovely.family.android.Pages.Navigation.AppNavigation
import lovely.family.android.Pages.Navigation.AppNavigationVM
import lovely.family.android.ui.theme.LovelyFamilyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: AppNavigationVM = viewModel()

            LovelyFamilyTheme {
                AppNavigation(
                    viewModel = viewModel
                )
            }
        }
    }
}