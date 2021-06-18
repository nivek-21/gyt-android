package principal.app.repositories.responses.dto

data class Tour(
    val id: Int,
    val country: Country,
    val place: String,
    val title: String,
    val duration: String,
    val description: String,
    val meeting_point: String,
    val price: Float,
//    val details: String,
    val comments_count: Int,
    val stars: Float,
    val categories: List<Category>,
    val images: List<Image>,
    val favorite: Boolean
)