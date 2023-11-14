package school.sptech.zup.presenter.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.Onboarding2
import school.sptech.zup.R
import school.sptech.zup.TelaInicial
import school.sptech.zup.databinding.ActivityCadastroNomeBinding
import school.sptech.zup.databinding.ActivityOnboarding1Binding
import school.sptech.zup.domain.model.DadosTelaCadastroNomeRequest
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil1Request

class CadastroNome : AppCompatActivity() {

    val binding by lazy {

        ActivityCadastroNomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonContinuar.setOnClickListener {

            val cadastroSenha = Intent(this, CadastroSenha::class.java)

            val nome = binding.nomeEditText.text.toString()
            val sobrenome = binding.sobrenomeEditText.text.toString()
            val username = binding.usernameEditText.text.toString()
            val checkBoxInfluencer = binding.checkBoxInfluencer.isChecked

            val dados = DadosTelaCadastroNomeRequest(
                nome = nome,
                sobrenome = sobrenome,
                username = username,
                influencer = checkBoxInfluencer
            )

            cadastroSenha.putExtra("dados", dados)
            startActivity(cadastroSenha)
        }

        binding.buttonCancel.setOnClickListener {

            val intent = Intent(this, TelaInicial::class.java)
            startActivity(intent)

        }

    }
}