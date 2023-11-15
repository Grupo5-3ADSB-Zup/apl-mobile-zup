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
import school.sptech.zup.domain.model.DadosTelaCadastroCPF
import school.sptech.zup.domain.model.RegisterRequest
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.network.ServiceProvider.service
import school.sptech.zup.presenter.feed.Feed
import school.sptech.zup.ui.PerfilUsuarioComum
import school.sptech.zup.ui.PerfilUsuarioSemFormulario


@Suppress("DEPRECATION")
class CadastroCPF : AppCompatActivity() {



    val binding by lazy {

        ActivityCadastroCpfBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonContinuar.setOnClickListener {

            val dadosCadastroNome =
                intent.getSerializableExtra("dados") as? DadosTelaCadastroCPF

            if (binding.aceitarCheckBox.isChecked == false){
                mostrarErroMensagem("Não é possível prosseguir sem aceitar os termos")

                reiniciarCadastroCPF()
            }

                val extras = intent.extras
                if (extras != null) {
                val nome = dadosCadastroNome?.nome.toString()
                val sobrenome = dadosCadastroNome?.sobrenome.toString()
                val username = dadosCadastroNome?.username.toString()
                val senha = dadosCadastroNome?.senha.toString()
                val influencer = dadosCadastroNome?.influencer
                val cpf = binding.cpfEditText.text.toString()
                val cnpj = binding.cnpjEditText.text.toString()
                val nomeInteiro = "$nome  $sobrenome"

                    val registerUser = RegisterRequest(nomeInteiro, null, username,senha,
                        influencer, false,
                        null, cpf, cnpj)

                    val call = service.saveUser(registerUser)
                    call.enqueue(object : Callback<RegisterResponse> {
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            if (response.isSuccessful) {
                                val cadastroResponse = response.body()

                                if (cadastroResponse != null) {

                                    val sessao = Sessao

                                    sessao.nome = nomeInteiro
                                    sessao.username = username
                                    sessao.idUsuario = cadastroResponse?.id.toString()

                                    perfilUsuarioComum()
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

    private fun reiniciarCadastroCPF() {
        val intent = Intent(this, CadastroCPF::class.java)
        startActivity(intent)
    }

    private fun perfilUsuarioComum() {
        val intent = Intent(this, PerfilUsuarioComum::class.java)
        startActivity(intent)
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}