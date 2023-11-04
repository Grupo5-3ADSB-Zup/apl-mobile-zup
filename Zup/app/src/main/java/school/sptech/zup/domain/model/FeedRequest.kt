package school.sptech.zup.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedRequest(
    val id: Long,
    val titulo: String,
    val descricao: String,
    val emissora: String,
    val link: String,
    val dtNoticia: String,
    val fotoNoticia: Byte
): Parcelable
