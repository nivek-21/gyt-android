package principal.app.repositories.responses.dto

data class Category(
    val id: Int,
    val name: String,
    val description: String?,
    val bg_color: String,
    val text_color: String
)