package school.sptech.zup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import school.sptech.zup.databinding.ActivityTelaInicialBinding

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

            val telaCadastro = Intent(this, CadastroNome::class.java)

            startActivity(telaCadastro)
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