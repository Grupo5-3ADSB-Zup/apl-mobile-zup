package school.sptech.zup.data.model

import java.io.Serializable

data class PerfilUsuarioResponse(
    val nome: String,
    val foto: Byte?,
    val IdPerfil: Long,
    val LinkYoutube: String?,
    val LinkInstagram: String?,
    val LinkTikTok: String?,
    val DescPerfil: String

) : Serializable
