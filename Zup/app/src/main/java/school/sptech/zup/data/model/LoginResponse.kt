package school.sptech.zup.data.model.response

import java.io.Serializable

data class LoginResponse(
    val id: Long,
    val nome: String,
    val email: String,
    val token: String,
) : Serializable
