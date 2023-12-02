package school.sptech.zup.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import school.sptech.zup.R
import school.sptech.zup.databinding.ActivityFormularioPerfil3Binding
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil2Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil3Request
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.presenter.feed.Feed

@Suppress("DEPRECATION")
class FormularioPerfil3 : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioPerfil3Binding.inflate(layoutInflater)
    }

    val sessao = Sessao

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val dadosFormularioParte2 =
            intent.getSerializableExtra("dados") as? DadosTelaFormularioPerfil2Request

        val radioGroup = findViewById<RadioGroup>(R.id.meuRadioGroup)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            var selecao = ""
            when (checkedId) {
                R.id.meu_RadioButton_valor1 -> {
                    val radioButton1 = findViewById<RadioButton>(R.id.meu_RadioButton_valor1)
                    val textoRadioButton1 = radioButton1.text.toString()
                    selecao = textoRadioButton1
                }

                R.id.meu_RadioButton_valor2 -> {
                    val radioButton2 = findViewById<RadioButton>(R.id.meu_RadioButton_valor2)
                    val textoRadioButton2 = radioButton2.text.toString()
                    selecao = textoRadioButton2
                }

                R.id.meu_RadioButton_valor3 -> {
                    val radioButton3 = findViewById<RadioButton>(R.id.meu_RadioButton_valor3)
                    val textoRadioButton3 = radioButton3.text.toString()
                    selecao = textoRadioButton3
                }

                else -> {
                }
            }
            val dados = DadosTelaFormularioPerfil3Request(
                idUsuario = dadosFormularioParte2?.idUsuario.toString(),
                radioButtonTelaFormulario1 = dadosFormularioParte2?.radioButtonTelaFormulario1.toString(),
                radioButtonTelaFormulario2 = dadosFormularioParte2?.radioButtonTelaFormulario2.toString(),
                selecao
            )

            binding.buttonPassarProximaEtapaFormulario4.setOnClickListener {
                formularioParte4(dados)
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

    private fun telaFormulario2() {
        val intent = Intent(this, FormularioPerfil2::class.java)
        startActivity(intent)
    }

    private fun formularioParte4(dados: DadosTelaFormularioPerfil3Request) {
        val intent = Intent(this, FormularioPerfil4::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
    }
}