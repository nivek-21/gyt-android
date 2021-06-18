package principal.app.repositories.responses.dto

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val type: Int,
    val birthdate: String?,
    val country_id: String?,
    val genre_id: String?
)
