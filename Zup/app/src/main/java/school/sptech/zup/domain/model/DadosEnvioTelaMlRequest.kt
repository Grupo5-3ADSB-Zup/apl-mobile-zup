package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosEnvioTelaMlRequest(
    val id: Integer,
    val titulo: String,
    val descricao: String,
    val link: String,
    val emissora: String,
    val dtNoticia: String,
    val fotoNoticia: String?,
) : Serializable
