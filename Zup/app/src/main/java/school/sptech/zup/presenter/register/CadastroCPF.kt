package school.sptech.zup.presenter.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.data.model.RegisterResponse
import school.sptech.zup.databinding.ActivityCadastroCpfBinding
import school.sptech.zup.network.ServiceProvider.service
import school.sptech.zup.presenter.feed.Feed
import school.sptech.zup.ui.PerfilUsuarioComum
import school.sptech.zup.ui.PerfilUsuarioSemFormulario


class CadastroCPF : AppCompatActivity() {



    val binding by lazy {

        ActivityCadastroCpfBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonContinuar.setOnClickListener {

            val PerfilUsuarioSemFormulario = Intent(this, PerfilUsuarioSemFormulario::class.java)

            startActivity(PerfilUsuarioSemFormulario)

                val extras = intent.extras
                if (extras != null) {
                val nome = extras.getString("nome")
                val sobrenome = extras.getString("sobrenome")
                val username = extras.getString("username")
                val senha = extras.getString("senha")
                val confirmarSenha = extras.getString("confirmarSenha")
                val cpf = binding.cpfEditText.text.toString()
                    val cnpj = binding.cnpjEditText.text.toString()
                val nomeInteiro = nome + sobrenome

                    val registerUser = RegisterResponse(nomeInteiro, username,senha, cpf, cnpj )

                    val call = service.saveUser(registerUser)
                    call.enqueue(object : Callback<RegisterResponse> {
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            if (response.isSuccessful) {
                                val loginResponse = response.body()

                                if (loginResponse != null) {

                                    paginaProfile()
                                } else {
                                    mostrarErroMensagem("Credenciais inválidas")
                                }
                            } else {
                                mostrarErroMensagem("Erro na solicitação")
                            }
                        }

                        override fun onFailure(call: Call<RegisterResponse>?, t: Throwable?) {
                            mostrarErroMensagem("Erro na rede: ${t?.message}")
                        }
                    })
            } else {

            }
        }
    }
    private fun paginaProfile() {
        val intent = Intent(this, PerfilUsuarioComum::class.java)
        startActivity(intent)
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}