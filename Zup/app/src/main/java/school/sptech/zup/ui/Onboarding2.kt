package school.sptech.zup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import school.sptech.zup.databinding.ActivityOnboarding2Binding
import school.sptech.zup.presenter.register.CadastroNome

class Onboarding2 : AppCompatActivity() {

    val binding by lazy {

        ActivityOnboarding2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.proximoOnboarding2.setOnClickListener {

            val onboarding2 = Intent(this, Onboarding3::class.java)

            startActivity(onboarding2)
        }

        binding.tvSkip.setOnClickListener {

            val telaCadastroNome = Intent(this, CadastroNome::class.java)

            startActivity(telaCadastroNome)
        }
    }
}