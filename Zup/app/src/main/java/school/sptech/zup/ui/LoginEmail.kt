package school.sptech.zup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import school.sptech.zup.TelaInicial
import school.sptech.zup.databinding.ActivityLoginEmailBinding

class LoginEmail : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginEmailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonContinuar.setOnClickListener {
            val emailInput = binding.editTextEmail.text.toString()
            val senhaInput = binding.editTextSenha.text.toString()

            if (credencial(emailInput, senhaInput)) {
                iniciarLogin()
            } else {
                mostrarErroMensagem("Credenciais incorretas. Verifique seu email e senha.")
            }
        }

        binding.buttonCancel.setOnClickListener {

            val intent = Intent(this, TelaInicial::class.java)
            startActivity(intent)

        }

        binding.buttonVoltar.setOnClickListener {

            val intent = Intent(this, TelaInicial::class.java)
            startActivity(intent)

        }
    }

    private fun credencial(email: String, senha: String): Boolean {
        return (email == "joao@email.com" && senha == "123123") || (email == "monteiro@email.com" && senha == "123")
    }

    private fun iniciarLogin() {
        val intent = Intent(this, PerfilUsuarioComum::class.java)
        startActivity(intent)
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
