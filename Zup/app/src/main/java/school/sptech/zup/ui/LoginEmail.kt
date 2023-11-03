package school.sptech.zup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import school.sptech.zup.TelaInicial
import school.sptech.zup.data.api.ServiceApi
import school.sptech.zup.data.model.request.LoginRequest
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityLoginEmailBinding
import school.sptech.zup.domain.model.FeedRequest
import school.sptech.zup.presenter.feed.Feed
import java.io.IOException

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

            var login = LoginRequest(emailInput, senhaInput)

            val get = ServiceApi.loginUser(login)

            ServiceApi.client.newCall(get).enqueue(object : okhttp3.Callback {
                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()

                        val gson = Gson()
                        val loginResponse = gson.fromJson(responseBody, LoginResponse::class.java)

                        val dados = loginResponse

                        iniciarLogin(dados)

                    } else {
                    }
                }

                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    e.printStackTrace()
                }
            })
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

    private fun iniciarLogin(dados: LoginResponse?) {
        val intent = Intent(this, PerfilUsuarioComum::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
    }

    private fun credencial(email: String, senha: String): Boolean {
        return (email == "joao@email.com" && senha == "123123") || (email == "monteiro@email.com" && senha == "123")
    }

    //private fun iniciarLogin(this, ) {
    //    val intent = Intent(this, PerfilUsuarioComum::class.java)
    //    startActivity(intent)
    //}

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
