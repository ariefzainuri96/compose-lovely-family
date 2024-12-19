package simart.umby.android.data.repository

import retrofit2.Response
import simart.umby.android.data.remote.MyApi
import simart.umby.android.data.response.LoginResponse

interface LoginRepository {
    suspend fun login(): Response<LoginResponse>
}

class LoginRepositoryImpl(private val api: MyApi): LoginRepository {
    override suspend fun login(): Response<LoginResponse> = api.login()
}