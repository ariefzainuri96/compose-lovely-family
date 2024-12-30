package lovely.family.android.Pages.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import lovely.family.android.Components.CustomTextField
import lovely.family.android.Components.Theme.ChickenPie400
import lovely.family.android.R
import lovely.family.android.Utils.RequestState

@Composable
fun LoginView(navController: NavController) {
    val viewModel: LoginVM = hiltViewModel()
    val loginForm = viewModel.loginForm.collectAsState()
    val loginState = viewModel.loginState.collectAsState()
    val loginFormError = viewModel.loginFormError.collectAsState()

    LaunchedEffect(loginState.value) {
        if (loginState.value == RequestState.SUCCESS) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_logo),
                    contentDescription = "Lovely Family",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )

                Text("Welcome back!", style = ChickenPie400, fontSize = 30.sp)

                CustomTextField(
                    value = loginForm.value.email,
                    onValueChange = {
                        viewModel.updateLoginForm { copy(email = it) }
                    },
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                    placeholder = "Email",
                    error = loginFormError.value.firstOrNull { it.key == "email" }?.error
                )

                CustomTextField(
                    value = loginForm.value.password,
                    onValueChange = {
                        viewModel.updateLoginForm { copy(password = it) }
                    },
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                    placeholder = "Password",
                    error = loginFormError.value.firstOrNull { it.key == "password" }?.error,
                    onDone = {
                        viewModel.login()
                    }
                )
            }

        }
    }
}