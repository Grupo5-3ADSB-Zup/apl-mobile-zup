package school.sptech.zup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import school.sptech.zup.databinding.ActivityOnboarding3Binding
import school.sptech.zup.presenter.register.CadastroNome

class Onboarding3 : AppCompatActivity() {
    val binding by lazy {

        ActivityOnboarding3Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.proximoOnboarding3.setOnClickListener {

            val onboarding3 = Intent(this, CadastroNome::class.java)

            startActivity(onboarding3)
        }

        binding.tvSkip.setOnClickListener {

            val telaCadastroNome = Intent(this, CadastroNome::class.java)

            startActivity(telaCadastroNome)
        }
    }

}