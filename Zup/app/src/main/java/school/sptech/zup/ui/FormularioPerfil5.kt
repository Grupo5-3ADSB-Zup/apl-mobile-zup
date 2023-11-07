package school.sptech.zup.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import school.sptech.zup.R
import school.sptech.zup.data.api.ServiceApi
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityFormularioPerfil3Binding
import school.sptech.zup.databinding.ActivityFormularioPerfil5Binding
import school.sptech.zup.domain.model.DadosEnvioApiFormularioPerfil
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil1Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil2Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil3Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil4Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil5Request
import java.io.IOException

@Suppress("DEPRECATION")
class FormularioPerfil5 : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormularioPerfil5Binding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonVoltar.setOnClickListener{
            telaFormulario4()
        }

        val dadosFormularioParte4 =
            intent.getSerializableExtra("dados") as? DadosTelaFormularioPerfil4Request

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

            val dadosEnvioApiFormularioPerfil = DadosEnvioApiFormularioPerfil(
                idUsuario = dadosFormularioParte4?.idUsuario.toString(),
                tela1 = dadosFormularioParte4?.radioButtonTelaFormulario1.toString(),
                tela2 = dadosFormularioParte4?.radioButtonTelaFormulario2.toString(),
                tela3 = dadosFormularioParte4?.radioButtonTelaFormulario4.toString(),
                tela4 = dadosFormularioParte4?.radioButtonTelaFormulario4.toString(),
                tela5 = selecao
            )

            binding.buttonConcluir.setOnClickListener{
                envioBaseDados(dadosEnvioApiFormularioPerfil)
            }
        }
    }

    private fun telaFormulario4() {
        val intent = Intent(this, FormularioPerfil4::class.java)
        startActivity(intent)
    }

    private fun envioBaseDados(dadosEnvioApiFormularioPerfil: DadosEnvioApiFormularioPerfil) {
        // fazer nova chamada
    }
}