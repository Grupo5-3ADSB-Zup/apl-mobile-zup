package school.sptech.zup.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class FeedRequest(
    val id: Long,
    val titulo: String,
    val descricao: String,
    val link: String,
    val emissora: String,
    val dtNoticia: String,
    val fotoNoticia: Byte,
) : Serializable
