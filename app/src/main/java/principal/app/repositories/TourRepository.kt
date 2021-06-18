package principal.app.repositories

import principal.app.repositories.api.TourApi
import principal.app.repositories.responses.TourResponse
import principal.app.repositories.responses.dto.Tour
import retrofit2.Call

class TourRepository : BaseRepository() {
    private var api: TourApi = retrofitService.buildApi(TourApi::class.java)

    fun getAll(): Call<List<Tour>> {
        return api.getAll()
    }

    fun search(place: String, startHour: String, endHour: String): Call<List<Tour>> {
        return api.search(place, startHour, endHour)
    }
}