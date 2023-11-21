package school.sptech.zup.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityPerfilUsuarioComumBinding
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.presenter.feed.Feed


@Suppress("DEPRECATION")
class PerfilUsuarioComum : AppCompatActivity() {

    private val binding by lazy {
        ActivityPerfilUsuarioComumBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sessao = Sessao

        var dadosLogin = intent.getSerializableExtra("dados") as? LoginResponse

        if (sessao.nome == null) {
            binding.nomeUsuario.text = dadosLogin?.nome.toString()
        } else binding.nomeUsuario.text = sessao?.nome.toString()

        binding.buttonToFormularioPerfil.setOnClickListener {

            iniciarFormulario(dadosLogin)
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

        menuItemSettings.setOnMenuItemClickListener{
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

    private fun iniciarFormulario(dadosLogin: LoginResponse?) {
        val intent = Intent(this, FormularioPerfil1::class.java)
        intent.putExtra("dadosLogin", dadosLogin)
        startActivity(intent)
    }
}