package school.sptech.zup.data.model

import school.sptech.zup.domain.model.FeedRequest

data class FeedResponse(
    val id: Long,
    val titulo: String,
    val descricao: String,
    val link: String,
    val emissora: String,
    val dtNoticia: String,
    val fotoNoticia: Byte,
)

