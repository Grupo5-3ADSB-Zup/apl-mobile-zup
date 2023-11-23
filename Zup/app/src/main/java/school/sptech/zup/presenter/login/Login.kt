package school.sptech.zup.presenter.login

import android.content.Context
import school.sptech.zup.presenter.feed.Feed
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.TelaInicial
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityLoginBinding
import school.sptech.zup.domain.model.LoginRequest
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.network.ServiceProvider.service

class Login : AppCompatActivity() {


    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sessao = Sessao

        binding.buttonContinuar.setOnClickListener {
            val emailInput = binding.editTextEmail.text.toString()
            val senhaInput = binding.editTextSenha.text.toString()


            val loginRequest = LoginRequest(emailInput, senhaInput)

            val call = service.login(loginRequest)

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>, response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()

                        if (loginResponse != null) {
                                addsharedPreferences(loginResponse)
                                iniciarLogin(emailInput, sessao, loginResponse)

                        } else {
                            mostrarErroMensagem("Credenciais inválidas")
                        }
                    } else {
                        mostrarErroMensagem("Erro na solicitação")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    mostrarErroMensagem("Erro na rede: ${t.message}")
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

    private fun addsharedPreferences(loginResponse: LoginResponse) {
        val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

        // Cria um editor para modificar o SharedPreferences
        val editor = sharedPreferences.edit()

        // Adiciona dados chave-valor
        editor.putString("nome", loginResponse.nome)
        editor.putString("token", loginResponse.token)

        // Salva as mudanças
        editor.apply()
    }

    private fun credencial(email: String, senha: String): Boolean {
        return (email == "joao@email.com" && senha == "123123") || (email == "monteiro@email.com" && senha == "123")
    }

    private fun iniciarLogin(emailInput: String, sessao: Sessao, loginResponse: LoginResponse) {
        sessao.nome = loginResponse?.nome.toString()
        sessao.idUsuario = loginResponse?.id.toString()
        sessao.token = loginResponse?.token.toString()

        val intent = Intent(this, Feed::class.java)
        startActivity(intent)
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
