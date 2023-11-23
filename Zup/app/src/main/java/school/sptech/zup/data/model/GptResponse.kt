package school.sptech.zup.data.model

import java.io.Serializable

data class GptResponse(
    val id: Integer,
    val resposta: String,
) : Serializable
