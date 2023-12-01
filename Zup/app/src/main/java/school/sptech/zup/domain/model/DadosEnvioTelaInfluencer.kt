package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosEnvioTelaInfluencer(
    val nome: String,
    val idPerfil: Long,
    val linkYoutube: String,
    val linkInstagram: String,
    val linkTikTok: String,
    val descPerfil: String,
    val foto: String?
) : Serializable
