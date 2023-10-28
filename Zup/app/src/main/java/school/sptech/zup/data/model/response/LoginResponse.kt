package school.sptech.zup.data.model.response

data class LoginResponse(
    val id: Long,
    val nome: String,
    val email: String,
    val token: String,
)
