package school.sptech.zup.presenter.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import school.sptech.zup.TelaInicial
import school.sptech.zup.databinding.ActivityCadastroSenhaBinding
import school.sptech.zup.domain.model.DadosTelaCadastroCPF
import school.sptech.zup.domain.model.DadosTelaCadastroNomeRequest

@Suppress("DEPRECATION")
class CadastroSenha : AppCompatActivity() {

    val binding by lazy {

        ActivityCadastroSenhaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonContinuar.setOnClickListener {

            val dadosCadastroNome =
                intent.getSerializableExtra("dados") as? DadosTelaCadastroNomeRequest

            val senha = binding.senhaEditText.text.toString()
            val confirmarSenha = binding.confirmarSenhaEditText.text.toString()

            if (senha.equals(confirmarSenha)){
                val cadastroCPF = Intent(this, CadastroCPF::class.java)

                val dados = DadosTelaCadastroCPF(
                    nome = dadosCadastroNome?.nome.toString(),
                    sobrenome = dadosCadastroNome?.sobrenome.toString(),
                    username = dadosCadastroNome?.username.toString(),
                    influencer = dadosCadastroNome?.influencer,
                    senha = senha
                )
                cadastroCPF.putExtra("dados", dados)
                startActivity(cadastroCPF)
            }
            else{
                mostrarErroMensagem("Senhas Diferentes")
            }
        }

        binding.buttonCancel.setOnClickListener {

            val intent = Intent(this, TelaInicial::class.java)
            startActivity(intent)

        }

    }
    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}