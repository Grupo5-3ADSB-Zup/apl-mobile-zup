package school.sptech.zup.domain.model

import android.widget.RadioGroup
import java.io.Serializable

data class DadosTelaFormularioPerfil1Request(
    val idUsuario: String,
    val radioButtonTelaFormulario1: String,
) : Serializable
