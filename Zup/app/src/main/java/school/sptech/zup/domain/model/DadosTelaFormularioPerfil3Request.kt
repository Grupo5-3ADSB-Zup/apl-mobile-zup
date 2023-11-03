package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaFormularioPerfil3Request(
    val radioButtonTelaFormulario2: DadosTelaFormularioPerfil2Request?,
    val radioButtonTelaFormulario3: String,
) : Serializable
