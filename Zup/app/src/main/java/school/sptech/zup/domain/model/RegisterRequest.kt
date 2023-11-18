package school.sptech.zup.domain.model

import java.io.Serializable

data class RegisterRequest(
    val nome: String,
    val email: String? = null,
    val username: String?,
    val senha: String?,
    val influencer: Boolean?,
    val autenticado: Boolean = false,
    val foto: Byte? = null,
    val cpf: String,
    val cnpj: String? = null,
) : Serializable
