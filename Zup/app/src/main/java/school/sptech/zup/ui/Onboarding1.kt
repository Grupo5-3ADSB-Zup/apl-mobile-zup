package school.sptech.zup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import school.sptech.zup.databinding.ActivityOnboarding1Binding
import school.sptech.zup.databinding.ActivityTelaInicialBinding
import school.sptech.zup.ui.CadastroNome

class Onboarding1 : AppCompatActivity() {

    val binding by lazy {

        ActivityOnboarding1Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.proximoOnboarding1.setOnClickListener {

            val onboarding2 = Intent(this, Onboarding2::class.java)

            startActivity(onboarding2)
        }

        binding.tvSkip.setOnClickListener {

            val telaCadastroNome = Intent(this, CadastroNome::class.java)

            startActivity(telaCadastroNome)
        }
    }

}