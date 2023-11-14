package school.sptech.zup.presenter.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

            val cadastroCPF = Intent(this, CadastroCPF::class.java)

            val senha = binding.senhaEditText.text.toString()
            val confirmarSenha = binding.senhaEditText.text.toString()

            if (senha.equals(confirmarSenha)){
                val dados = DadosTelaCadastroCPF(
                    nome = dadosCadastroNome?.nome.toString(),
                    sobrenome = dadosCadastroNome?.sobrenome.toString(),
                    username = dadosCadastroNome?.username.toString(),
                    influencer = dadosCadastroNome?.influencer,
                    senha = senha
                )
                cadastroCPF.putExtra("dados", dados)
            }
            startActivity(cadastroCPF)
        }

        binding.buttonCancel.setOnClickListener {

            val intent = Intent(this, TelaInicial::class.java)
            startActivity(intent)

        }

    }
}