package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaFormularioPerfil4Request(
    val idUsuario: String,
    val radioButtonTelaFormulario1: String?,
    val radioButtonTelaFormulario2: String?,
    val radioButtonTelaFormulario3: String?,
    val radioButtonTelaFormulario4: String,
) : Serializable
