package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaCadastroCPF(
    val nome: String?,
    val sobrenome: String?,
    val username: String?,
    val influencer: Boolean?,
    val senha: String?
) : Serializable
