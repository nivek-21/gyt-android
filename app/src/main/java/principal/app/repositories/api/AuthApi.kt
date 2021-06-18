package principal.app.repositories.api

import principal.app.repositories.responses.dto.Auth
import principal.app.repositories.responses.dto.Login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("api/sanctum/token")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_name") device: String
    ): Call<Auth>

    @FormUrlEncoded
    @POST("api/register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String,
        @Field("device_name") device: String,
    ): Call<Auth>

}