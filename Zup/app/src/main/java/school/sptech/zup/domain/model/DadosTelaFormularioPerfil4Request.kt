package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaFormularioPerfil4Request(
    val radioButtonTelaFormulario3: DadosTelaFormularioPerfil3Request?,
    val radioButtonTelaFormulario4: String,
) : Serializable
