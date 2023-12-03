package school.sptech.zup.ui

import java.io.Serializable

data class Usuario(
    val id: Long,
    val nome : String,
    val email: String,
    val username: String,
    val senha: String,
    val foto: String
) : Serializable
