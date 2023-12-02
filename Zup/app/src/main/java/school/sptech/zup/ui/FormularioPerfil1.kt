package school.sptech.zup.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityFormularioPerfil1Binding
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil1Request
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.presenter.feed.Feed

@Suppress("DEPRECATION")
class FormularioPerfil1 : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioPerfil1Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dadosLogin = intent.getSerializableExtra("dados") as? LoginResponse


        val sessao = Sessao

        val radioGroup = findViewById<RadioGroup>(R.id.meuRadioGroup)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            var selecao = ""
            when (checkedId) {
                R.id.meu_RadioButton_sim -> {
                    val radioButton1 = findViewById<RadioButton>(R.id.meu_RadioButton_sim)
                    val textoRadioButton1 = radioButton1.text.toString()
                    selecao = textoRadioButton1
                }

                R.id.meu_RadioButton_nao -> {
                    val radioButton2 = findViewById<RadioButton>(R.id.meu_RadioButton_nao)
                    val textoRadioButton2 = radioButton2.text.toString()
                    selecao = textoRadioButton2
                }

                else -> {
                }
            }
            val dados = DadosTelaFormularioPerfil1Request(
                if (dadosLogin == null) sessao?.idUsuario.toString() else dadosLogin?.id.toString(),
                selecao
            )

            binding.buttonPassarProximaEtapaFormulario2.setOnClickListener {
                formularioParte2(dados)
            }
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

        menuItemSettings.setOnMenuItemClickListener{
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

    private fun telaInicio() {
        val intent = Intent(this, FormularioPerfil1::class.java)
        startActivity(intent)
    }

    private fun formularioParte2(dados: DadosTelaFormularioPerfil1Request) {
        val intent = Intent(this, FormularioPerfil2::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
    }
}