package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaCadastroNomeRequest(
    val nome: String?,
    val sobrenome: String?,
    val username: String?,
    val influencer: Boolean?
) : Serializable
