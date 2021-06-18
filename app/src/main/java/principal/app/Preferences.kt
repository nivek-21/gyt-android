package principal.app

import android.content.SharedPreferences
import principal.app.repositories.responses.dto.User

class Preferences(
    private val storage: SharedPreferences?
) {
    fun saveUser(user: User) {
        this.saveInt("id", user.id)
        this.saveString("name", user.name)
        this.saveString("email", user.email)
        this.saveInt("type", user.type)
        user.birthdate?.let { this.saveString("birthdate", it) }
        user.country_id?.let { this.saveString("country_id", it) }
        user.genre_id?.let { this.saveString("genre_id", it) }
    }

    fun saveToken(token: String) {
        this.saveString("token", token)
    }

    fun getToken(): String? {
        return this.getString("token")
    }

    fun getLogged(): Boolean? {
        return this.getBoolean("isLogged")
    }

    fun logged() {
        this.saveBoolean("isLogged", true)
    }

    fun loggedout() {
        this.saveBoolean("isLogged", false)
    }

    fun getUser(): User {
        return User(
            this.getInt("id")!!,
            this.getString("name")!!,
            this.getString("email")!!,
            this.getInt("type")!!,
            this.getString("birthdate"),
            this.getString("country_id"),
            this.getString("genre_id")
        )
    }

    private fun saveString(key: String, value: String) {
        storage?.edit()?.putString(key, value)?.apply()
    }

    private fun saveInt(key: String, value: Int) {
        storage?.edit()?.putInt(key, value)?.apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        storage?.edit()?.putBoolean(key, value)?.apply()
    }

    fun getString(key: String): String? {
        return storage?.getString(key, "")
    }

    fun getInt(key: String): Int? {
        return storage?.getInt(key, -1)
    }

    fun getBoolean(key: String): Boolean? {
        return storage?.getBoolean(key, false)
    }
}