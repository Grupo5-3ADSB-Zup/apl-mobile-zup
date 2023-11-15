package school.sptech.zup.ui

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

@Suppress("DEPRECATION")
class FormularioPerfil1 : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioPerfil1Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dadosLogin = intent.getSerializableExtra("dados") as? LoginResponse

        binding.buttonVoltar.setOnClickListener{
            telaInicio()
        }

        val sessao = Sessao

        val radioGroup = findViewById<RadioGroup>(R.id.meuRadioGroup)

        radioGroup.setOnCheckedChangeListener {group, checkedId ->
            var selecao = ""
            when (checkedId){
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
                selecao)

            binding.buttonPassarProximaEtapaFormulario2.setOnClickListener{
                formularioParte2(dados)
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