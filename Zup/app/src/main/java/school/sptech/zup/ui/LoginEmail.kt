package school.sptech.zup.ui

import school.sptech.zup.presenter.feed.Feed
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

                        iniciarLogin()

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

    private fun credencial(email: String, senha: String): Boolean {
        return (email == "joao@email.com" && senha == "123123") || (email == "monteiro@email.com" && senha == "123")
    }

    private fun iniciarLogin() {
        val intent = Intent(this, Feed::class.java)
        startActivity(intent)
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
