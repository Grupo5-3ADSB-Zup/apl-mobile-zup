package school.sptech.zup.presenter.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.R
import school.sptech.zup.TelaInicial
import school.sptech.zup.databinding.ActivityCadastroSenhaBinding

class CadastroSenha : AppCompatActivity() {

    val binding by lazy {

        ActivityCadastroSenhaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonContinuar.setOnClickListener {

            val cadastroCPF = Intent(this, CadastroCPF::class.java)

            val senha = binding.senhaEditText.text.toString()
            val confirmarSenha = binding.senhaEditText.text.toString()

            cadastroCPF.putExtra("senha", senha)
            cadastroCPF.putExtra("confirmarSenha", confirmarSenha)

            startActivity(cadastroCPF)
        }

        binding.buttonCancel.setOnClickListener {

            val intent = Intent(this, TelaInicial::class.java)
            startActivity(intent)

        }

    }
}