package school.sptech.zup.data.model

import androidx.annotation.IdRes
import school.sptech.zup.domain.model.FeedRequest
import java.io.Serializable

data class FeedResponse(
    val id: Integer,
    val titulo: String,
    val descricao: String,
    val link: String,
    val emissora: String,
    val dtNoticia: String,
    val fotoNoticia: Byte,
) : Serializable

