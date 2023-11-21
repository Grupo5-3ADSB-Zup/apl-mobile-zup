package school.sptech.zup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityBuscarInfluenciadoresBinding
import school.sptech.zup.databinding.ActivityTelaConfiguracao2Binding
import school.sptech.zup.domain.model.DadosTelaRefazerFormulario
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.presenter.feed.Feed

@Suppress("DEPRECATION")
class TelaConfiguracao2 : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaConfiguracao2Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
         val sessao = Sessao
         val dadosLogin = intent.getSerializableExtra("dados") as? LoginResponse

        val dados = DadosTelaRefazerFormulario(
            sessao?.idUsuario.toString()
        )


        binding.refazerTeste.setOnClickListener {
            formularioParte1(dados)
        }

        binding.buttonSair.setOnClickListener{
            retornarFeed()
        }

    }

    private fun retornarFeed() {
        val intent = Intent(this, Feed::class.java)
        startActivity(intent)
    }

    private fun formularioParte1(dados: DadosTelaRefazerFormulario) {
        val intent = Intent(this, FormularioPerfil1::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
    }
}