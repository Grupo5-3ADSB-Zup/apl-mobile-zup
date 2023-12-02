package school.sptech.zup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import school.sptech.zup.databinding.ActivityTelaInicialBinding
import school.sptech.zup.presenter.feed.Feed
import school.sptech.zup.presenter.login.Login

class TelaInicial : AppCompatActivity() {

    val binding by lazy {

        ActivityTelaInicialBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

        val valorNome = sharedPreferences.getString("nome", null)

        if(valorNome != null)
            iniciarLoginOffiline()
        else {
            binding.buttonLogin.setOnClickListener {

                val telaLogin = Intent(this, Login::class.java)

                startActivity(telaLogin)
            }

            binding.buttonCadastrar.setOnClickListener {

                val onboarding1 = Intent(this, Onboarding1::class.java)

                startActivity(onboarding1)
            }
        }
    }

    private fun iniciarLoginOffiline() {
        val intent = Intent(this, Feed::class.java)
        startActivity(intent)
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