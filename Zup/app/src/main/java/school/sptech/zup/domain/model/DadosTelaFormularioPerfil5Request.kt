package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosTelaFormularioPerfil5Request(
    val radioButtonTelaFormulario4: DadosTelaFormularioPerfil4Request?,
    val radioButtonTelaFormulario5: String,
) : Serializable
