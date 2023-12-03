package school.sptech.zup.data.model

import school.sptech.zup.ui.Usuario
import java.io.Serializable
import java.time.LocalDateTime

data class ComentarioResponse(
    val id: Long,
    val descricao: String,
    val dtComentario: LocalDateTime,
    val dtComentarioFormatada: String,
    val pesoComentario: Int,
    val usuario: Usuario
) : Serializable
