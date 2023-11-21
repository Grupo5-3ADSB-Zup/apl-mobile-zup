package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaFormularioPerfil5Request(
    val idUsuario: String,
    val radioButtonTelaFormulario1: String?,
    val radioButtonTelaFormulario2: String?,
    val radioButtonTelaFormulario3: String?,
    val radioButtonTelaFormulario4: String,
    val radioButtonTelaFormulario5: String,
) : Serializable
