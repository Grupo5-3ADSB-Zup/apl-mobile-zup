package school.sptech.zup.data.mapper

import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.domain.model.Feed

fun FeedResponse.toDomain(): Feed{
    return Feed(
        titulo = titulo,
        descricao = descricao,
        emissora = emissora,
        fotoNoticia = fotoNoticia
    )
}