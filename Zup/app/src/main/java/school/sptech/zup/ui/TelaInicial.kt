package school.sptech.zup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import school.sptech.zup.databinding.ActivityTelaInicialBinding
import school.sptech.zup.presenter.login.LoginEmail

class TelaInicial : AppCompatActivity() {

    val binding by lazy {

        ActivityTelaInicialBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {

            val telaLogin = Intent(this, LoginEmail::class.java)

            startActivity(telaLogin)
        }

        binding.buttonCadastrar.setOnClickListener {

            val onboarding1 = Intent(this, Onboarding1::class.java)

            startActivity(onboarding1)
        }
    }

//    fun entrarLogin(view: View) {
//        val intent = Intent(this, LoginEmail::class.java)
//        startActivity(intent)
//    }

//    fun entrarOnboarding(view: View) {
//        val intent = Intent(this, Onboarding1::class.java)
//        startActivity(intent)
//    }


}