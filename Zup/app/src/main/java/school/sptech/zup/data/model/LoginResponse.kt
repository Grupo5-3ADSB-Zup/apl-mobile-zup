package school.sptech.zup.data.model.response

import java.io.Serializable

data class LoginResponse(
    var id: Long,
    var nome: String,
    val email: String?,
    val token: String?,
) : Serializable
