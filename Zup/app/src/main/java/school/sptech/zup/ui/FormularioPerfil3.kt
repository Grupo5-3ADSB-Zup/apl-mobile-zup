package school.sptech.zup.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import school.sptech.zup.R
import school.sptech.zup.databinding.ActivityFormularioPerfil2Binding
import school.sptech.zup.databinding.ActivityFormularioPerfil3Binding
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil1Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil2Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil3Request

@Suppress("DEPRECATION")
class FormularioPerfil3 : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioPerfil3Binding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonVoltar.setOnClickListener{
            telaFormulario2()
        }

        val dadosFormularioParte2 =
            intent.getSerializableExtra("dados") as? DadosTelaFormularioPerfil2Request

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
            val dados = DadosTelaFormularioPerfil3Request(dadosFormularioParte2, selecao)

            binding.buttonPassarProximaEtapaFormulario4.setOnClickListener{
                formularioParte4(dados)
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