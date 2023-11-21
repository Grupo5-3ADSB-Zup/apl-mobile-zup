package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaFormularioPerfil2Request(
    val idUsuario: String,
    val radioButtonTelaFormulario1: String,
    val radioButtonTelaFormulario2: String
) : Serializable
