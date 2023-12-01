package school.sptech.zup.data.model

import java.io.Serializable

data class UsuarioResponse(
    val idUsuario : Long,
    val nome: String,
    val email: String?,
    val username : String,
    val senha : String,
    val influencer : Boolean,
    val autenticado : Boolean,
    val cpf : String,
    val cnpj : String,
    val admin : Int,
    val idPerfil: Long,
    val linkYoutube: String,
    val linkInstagram: String,
    val linkTikTok: String,
    val descPerfil: String,
    val foto: String?
) : Serializable
