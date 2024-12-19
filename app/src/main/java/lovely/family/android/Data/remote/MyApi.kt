package simart.umby.android.data.remote

import retrofit2.Response
import retrofit2.http.GET
import simart.umby.android.data.response.LoginResponse

interface MyApi {
    @GET("login")
    suspend fun login(): Response<LoginResponse>

}