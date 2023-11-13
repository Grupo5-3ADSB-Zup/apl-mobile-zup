package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaCadastroCPF(
    val nome:String?,
    val sobrenome:String?,
    val username:String?,
    val senha:String?
) : Serializable
