package principal.app.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import principal.app.repositories.TourRepository
import principal.app.repositories.responses.dto.Tour
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val repository: TourRepository = TourRepository()

    private var _tours = MutableLiveData<List<Tour>>()
    val tours: LiveData<List<Tour>>
        get() = _tours

    private var _notification = MutableLiveData<String>()
    val notification: LiveData<String>
        get() = _notification

    fun getAll() {
        repository.getAll().enqueue(object : Callback<List<Tour>> {
            override fun onResponse(call: Call<List<Tour>>, response: Response<List<Tour>>) {
                _tours.value = response.body()
            }

            override fun onFailure(call: Call<List<Tour>>, t: Throwable) {
                _notification.value = "Error en API"
            }
        })
    }

    fun search(place: String, startHour: String, endHour: String) {
        repository.search(place, startHour, endHour).enqueue(object : Callback<List<Tour>> {
            override fun onResponse(call: Call<List<Tour>>, response: Response<List<Tour>>) {
                Log.i("TOURS", response.body().toString())
                _tours.value = response.body()
            }

            override fun onFailure(call: Call<List<Tour>>, t: Throwable) {
                _notification.value = "Error en API"
            }
        })
    }
}


