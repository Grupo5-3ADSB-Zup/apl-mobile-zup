package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosEnvioTelaGptRequest(
    val id : Integer,
    var titulo : String

) : Serializable
