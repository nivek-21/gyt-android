package principal.app.repositories.api

import principal.app.repositories.responses.dto.Tour
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TourApi {
    @GET("api/tours")
    fun getAll(): Call<List<Tour>>

    @FormUrlEncoded
    @POST("api/tours/search")
    fun search(
        @Field("place") place: String,
        @Field("start_date") start_date: String,
        @Field("end_date") end_date: String
    ): Call<List<Tour>>
}