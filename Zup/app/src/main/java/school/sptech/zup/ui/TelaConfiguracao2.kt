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

        binding.buttonSair.setOnClickListener {
            retornarFeed()
        }

        val botaoNavBar = binding.navBar

        val menuItemHome = botaoNavBar.menu.findItem(R.id.botao_home)
        val menuItemPesquisar = botaoNavBar.menu.findItem(R.id.botao_search)
        val menuItemSettings = botaoNavBar.menu.findItem(R.id.botao_settings)
        val menuItemPerfil = botaoNavBar.menu.findItem(R.id.botao_profile)

        menuItemHome.setOnMenuItemClickListener {
            val intent = Intent(this, Feed::class.java)
            startActivity(intent)
            true
        }

        menuItemPesquisar.setOnMenuItemClickListener {
            val intent = Intent(this, BuscarInfluenciadores::class.java)
            startActivity(intent)
            true
        }

        menuItemSettings.setOnMenuItemClickListener {
            val intent = Intent(this, TelaConfiguracao2::class.java)
            startActivity(intent)
            true
        }

        menuItemPerfil.setOnMenuItemClickListener {
            val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
            startActivity(intent)
            true
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