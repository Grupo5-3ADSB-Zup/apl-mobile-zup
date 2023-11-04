package school.sptech.zup.data.model

data class LoginResponse(
    val id: Long,
    val nome: String,
    val email: String,
    val token: String,
)

