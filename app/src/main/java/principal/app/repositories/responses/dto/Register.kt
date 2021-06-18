package principal.app.repositories.responses.dto

data class Register(val name: String, val email: String, val password: String, val password_confirmation: String, val device_name: String)