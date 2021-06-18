package principal.app.repositories

import principal.app.repositories.api.AuthApi
import principal.app.repositories.responses.dto.Auth
import retrofit2.Call

class AuthRepository : BaseRepository() {

    private var api: AuthApi = retrofitService.buildApi(AuthApi::class.java)

    fun login(
        email: String,
        password: String
    ): Call<Auth> {
        return api.login(email, password, "android_device")
    }

    fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Call<Auth> {
        return api.register(name, email, password, passwordConfirmation, "android_device")
    }
}