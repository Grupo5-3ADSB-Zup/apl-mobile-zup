package school.sptech.zup.domain.model

import java.io.Serializable

data class CadastroDadosInfluencerRequest(
    val idUsuario: Int,
    val tiktok: String,
    val youtube: String,
    val instagram: String
) : Serializable
