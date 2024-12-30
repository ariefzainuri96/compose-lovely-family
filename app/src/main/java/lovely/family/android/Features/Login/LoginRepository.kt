package lovely.family.android.Features.Login

import lovely.family.android.Features.Login.Model.LoginFormModel
import retrofit2.Response
import simart.umby.android.data.remote.MyApi
import simart.umby.android.data.response.LoginResponse

interface LoginRepository {
    suspend fun login(data: LoginFormModel): Response<LoginResponse>
}

class LoginRepositoryImpl(private val api: MyApi): LoginRepository {
    override suspend fun login(data: LoginFormModel): Response<LoginResponse> = api.login()
}