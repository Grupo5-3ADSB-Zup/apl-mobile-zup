package school.sptech.zup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityPerfilUsuarioComumBinding
import school.sptech.zup.databinding.ActivityTelaConfiguracoesBinding
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil1Request
import school.sptech.zup.domain.model.DadosTelaRefazerFormulario
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.presenter.register.CadastroNome

//@Suppress("DEPRECATION")
class TelaConfiguracoes : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaConfiguracoesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        // val sessao = Sessao
        // val dadosLogin = intent.getSerializableExtra("dados") as? LoginResponse

        //val dados = DadosTelaRefazerFormulario(
        //    sessao?.idUsuario.toString()
        //)


        //binding.refazerTeste.setOnClickListener {
        //    formularioParte1(dados)
        //}

        val botaoNavBar = binding.navBar

        val menuItemHome = botaoNavBar.menu.findItem(R.id.botao_home)
        val menuItemPesquisar = botaoNavBar.menu.findItem(R.id.botao_search)
        val menuItemSettings = botaoNavBar.menu.findItem(R.id.botao_settings)
        val menuItemPerfil = botaoNavBar.menu.findItem(R.id.botao_profile)

//        menuItemHome.setOnMenuItemClickListener {
//            call
//            true
//        }

        menuItemPesquisar.setOnMenuItemClickListener {
            val intent = Intent(this, BuscarInfluenciadores::class.java)
            startActivity(intent)
            true
        }

        menuItemSettings.setOnMenuItemClickListener {
            val intent = Intent(this, TelaConfiguracoes::class.java)
            startActivity(intent)
            true
        }

        menuItemPerfil.setOnMenuItemClickListener {
            val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
            startActivity(intent)
            true
        }

    }

    //private fun formularioParte1(dados: DadosTelaRefazerFormulario) {
    //    val intent = Intent(this, FormularioPerfil1::class.java)
    //    intent.putExtra("dados", dados)
    //    startActivity(intent)
    //}
}