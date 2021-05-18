package principal.app.services

import principal.app.services.dto.Auth
import principal.app.services.dto.Login
import principal.app.services.dto.Register
import principal.app.services.dto.User
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("api/user")
    fun user(@Header("Authorization") token: String): Call<User>

    @POST("api/sanctum/token")
    fun login(@Body data: Login): Call<Auth>

    @POST("/api/logout")
    fun logout(@Header("Authorization") token: String): Call<String>

    @POST("api/register")
    fun register(@Body data: Register): Call<Auth>

}