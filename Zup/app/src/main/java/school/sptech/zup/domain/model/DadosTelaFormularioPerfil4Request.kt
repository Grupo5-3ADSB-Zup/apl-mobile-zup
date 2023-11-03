package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaFormularioPerfil4Request(
    val radioButtonTelaFormulario1: DadosTelaFormularioPerfil1Request?,
    val radioButtonTelaFormulario2: DadosTelaFormularioPerfil2Request?,
    val radioButtonTelaFormulario3: DadosTelaFormularioPerfil3Request?,
    val radioButtonTelaFormulario4: String,
) : Serializable
