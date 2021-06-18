package principal.app.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import principal.app.repositories.AuthRepository
import principal.app.repositories.api.AuthApi
import principal.app.services.RetrofitService
import principal.app.repositories.responses.dto.Auth
import principal.app.repositories.responses.dto.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {

    private val repository: AuthRepository = AuthRepository()

    private var _auth = MutableLiveData<Auth>()
    val auth: LiveData<Auth>
        get() = _auth

    private var _notification = MutableLiveData<String>()
    val notification: LiveData<String>
        get() = _notification

    fun login(
        email: String,
        password: String
    ) {
        repository.login(email, password).enqueue(object : Callback<Auth> {
            override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                val body = response.body()
                if (body != null) {
                    _auth.value = response.body()
                } else {
                    _notification.value = "¡Usuario no encontrado!"
                }
            }

            override fun onFailure(call: Call<Auth>, t: Throwable) {
                _notification.value = "¡Error de conexión!"
            }
        })
    }

    fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        repository.register(name, email, password, passwordConfirmation)
            .enqueue(object : Callback<Auth> {
                override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                    val body = response.body()

                    if (body != null) {
                        _auth.value = response.body()
                        _notification.value = "¡Registro realizado éxitosamente!"
                    } else {
                        _notification.value = "¡Algún dato está erroneo!"
                    }
                }

                override fun onFailure(call: Call<Auth>, t: Throwable) {
                    _notification.value = "¡Error de conexión!"
                }
            })
    }
}