package school.sptech.zup.domain.model

import java.io.Serializable

data class DadosEnvioApiFormularioPerfil(
    val idUsuario: String,
    val tela1: String,
    val tela2: String,
    val tela3: String,
    val tela4: String,
    val tela5: String

) : Serializable
