package school.sptech.zup.domain.model

import java.io.Serializable

data class GptRequest(
    val id: Int,
    val titulo: String,
    val pergunta: String
) : Serializable
