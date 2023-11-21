package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaFormularioPerfil3Request(
    val idUsuario: String,
    val radioButtonTelaFormulario1: String?,
    val radioButtonTelaFormulario2: String?,
    val radioButtonTelaFormulario3: String,
) : Serializable
