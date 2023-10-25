package school.sptech.zup.ui

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

            val CadastroCPF = Intent(this, CadastroCPF::class.java)

            startActivity(CadastroCPF)
        }

        binding.buttonCancel.setOnClickListener {

            val intent = Intent(this, TelaInicial::class.java)
            startActivity(intent)

        }

    }
}