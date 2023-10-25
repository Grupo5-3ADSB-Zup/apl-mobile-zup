package school.sptech.zup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.Onboarding2
import school.sptech.zup.R
import school.sptech.zup.TelaInicial
import school.sptech.zup.databinding.ActivityCadastroNomeBinding
import school.sptech.zup.databinding.ActivityOnboarding1Binding

class CadastroNome : AppCompatActivity() {

    val binding by lazy {

        ActivityCadastroNomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonContinuar.setOnClickListener {

            val CadastroSenha = Intent(this, CadastroSenha::class.java)

            startActivity(CadastroSenha)
        }

        binding.buttonCancel.setOnClickListener {

            val intent = Intent(this, TelaInicial::class.java)
            startActivity(intent)

        }

    }
}