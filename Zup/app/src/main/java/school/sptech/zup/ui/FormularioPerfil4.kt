package school.sptech.zup.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import school.sptech.zup.R
import school.sptech.zup.databinding.ActivityFormularioPerfil3Binding
import school.sptech.zup.databinding.ActivityFormularioPerfil4Binding
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil1Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil2Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil3Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil4Request


@Suppress("DEPRECATION")
class FormularioPerfil4 : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormularioPerfil4Binding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonVoltar.setOnClickListener{
            telaFormulario3()
        }

        val dadosFormularioParte3 =
            intent.getSerializableExtra("dados") as? DadosTelaFormularioPerfil3Request

        val radioGroup = findViewById<RadioGroup>(R.id.meuRadioGroup)

        radioGroup.setOnCheckedChangeListener {group, checkedId ->
            var selecao = ""
            when (checkedId){
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
            val dados = DadosTelaFormularioPerfil4Request(dadosFormularioParte3, selecao)

            binding.buttonPassarProximaEtapaFormulario5.setOnClickListener{
                formularioParte5(dados)
            }
        }
    }

    private fun telaFormulario3() {
        val intent = Intent(this, FormularioPerfil3::class.java)
        startActivity(intent)
    }

    private fun formularioParte5(dados: DadosTelaFormularioPerfil4Request) {
        val intent = Intent(this, FormularioPerfil5::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
    }
}