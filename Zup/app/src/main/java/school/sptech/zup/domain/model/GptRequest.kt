package school.sptech.zup.domain.model

import java.io.Serializable

data class GptRequest(
    val id: Integer,
    val titulo: String,
    val pergunta: String
) : Serializable
