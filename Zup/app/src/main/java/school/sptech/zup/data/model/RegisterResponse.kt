package school.sptech.zup.data.model

import java.io.Serializable

data class RegisterResponse(
    val id: Integer,
    val nome: String,
    val email: String? = null,
    val username: String?,
    val senha: String?,
    val influencer: Boolean,
    val autenticado: Boolean = false,
    val foto: Byte? = null,
    val cpf: String,
    val cnpj: String? = null,
) : Serializable
