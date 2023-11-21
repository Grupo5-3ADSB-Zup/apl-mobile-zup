package school.sptech.zup.data.mapper

import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.domain.model.FeedRequest

fun FeedResponse.toDomain(): FeedRequest {
    return FeedRequest(
        id = id,
        titulo = titulo,
        descricao = descricao,
        emissora = emissora,
        link = link,
        dtNoticia = dtNoticia,
        fotoNoticia = fotoNoticia

    )
}