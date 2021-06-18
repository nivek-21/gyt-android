package principal.app.services

import principal.app.Constant
import principal.app.repositories.responses.dto.Auth
import principal.app.repositories.responses.dto.Login
import principal.app.repositories.responses.dto.Register
import principal.app.repositories.responses.dto.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

class RetrofitService {

    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(api)
    }

//    @GET("api/user")
//    fun user(@Header("Authorization") token: String): Call<User>
//
//    @POST("api/sanctum/token")
//    fun login(@Body data: Login): Call<Auth>
//
//    @POST("/api/logout")
//    fun logout(@Header("Authorization") token: String): Call<String>
//
//    @POST("api/register")
//    fun register(@Body data: Register): Call<Auth>

}