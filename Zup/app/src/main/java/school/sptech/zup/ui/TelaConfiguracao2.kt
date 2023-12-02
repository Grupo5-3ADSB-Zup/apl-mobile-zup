package school.sptech.zup.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
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

        val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

        val valorUsername = sharedPreferences.getString("username", null)
        val valorNome = sharedPreferences.getString("nome", null)
        val valorIdUsuario = sharedPreferences.getLong("idUsuario", 0)

        val dados = LoginResponse(
            id = if (valorIdUsuario == null) sessao.idUsuario.toLong() else valorIdUsuario,
            nome = "",
            email = "",
            token = ""
        )


        binding.refazerTeste.setOnClickListener {
            formularioParte1(dados)
        }

        binding.buttonSair.setOnClickListener {
            sairZup()
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
            val intent = Intent(this, FiltroPerfil::class.java)
            startActivity(intent)
            true
        }

        menuItemSettings.setOnMenuItemClickListener {
            val intent = Intent(this, TelaConfiguracao2::class.java)
            startActivity(intent)
            true
        }

        menuItemPerfil.setOnMenuItemClickListener {
            val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

            val valorInfluencer = sharedPreferences.getBoolean("influencer", false)

            if(sessao.influencer == true || valorInfluencer == true){
                val intent = Intent(this, PerfilUsuarioInfluencer::class.java)
                startActivity(intent)
                true
            }else {
                val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
                startActivity(intent)
                true
            }
        }

    }

    private fun sairZup() {

        val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

        // Cria um editor para modificar o SharedPreferences
        val editor = sharedPreferences.edit()

        // Limpa todos os dados
        editor.clear()

        // Aplica as mudanças
        editor.apply()

        finishAffinity()
    }

    private fun formularioParte1(dados: LoginResponse) {
        val intent = Intent(this, FormularioPerfil1::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
    }
}