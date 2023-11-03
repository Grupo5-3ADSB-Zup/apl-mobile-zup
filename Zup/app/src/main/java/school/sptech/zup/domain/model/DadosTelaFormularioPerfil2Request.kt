package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaFormularioPerfil2Request(
    val radioButtonTelaFormulario1: DadosTelaFormularioPerfil1Request?,
    val radioButtonTelaFormulario2: String
) : Serializable
