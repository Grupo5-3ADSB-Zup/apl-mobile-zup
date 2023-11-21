package school.sptech.zup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityPerfilUsuarioSemFormularioBinding
import school.sptech.zup.databinding.ActivityTelaConfiguracao2Binding
import school.sptech.zup.domain.model.Sessao

class PerfilUsuarioSemFormulario : AppCompatActivity() {

    val binding by lazy {
        ActivityPerfilUsuarioSemFormularioBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sessao = Sessao
        binding.nomeUsuario.text = sessao?.nome.toString()
    }
}