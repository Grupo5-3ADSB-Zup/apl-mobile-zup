package school.sptech.zup.presenter.register

import android.content.Context
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
import school.sptech.zup.ui.CadastroInfluencer
import school.sptech.zup.ui.PerfilUsuarioComum



@Suppress("DEPRECATION")
class CadastroCPF : AppCompatActivity() {

    val binding by lazy {
        ActivityCadastroCpfBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var sessao = Sessao

        binding.buttonContinuar.setOnClickListener {

            val dadosCadastroNome =
                intent.getSerializableExtra("dados") as? DadosTelaCadastroCPF

                val extras = intent.extras
                if (extras != null && binding.aceitarCheckBox.isChecked) {
                val nome = dadosCadastroNome?.nome.toString()
                val sobrenome = dadosCadastroNome?.sobrenome.toString()
                val username = dadosCadastroNome?.username.toString()
                val senha = dadosCadastroNome?.senha.toString()
                val influencer = dadosCadastroNome?.influencer
                val cpf = binding.cpfEditText.text.toString()
                val cnpj = binding.cnpjEditText.text.toString()
                val nomeInteiro = "$nome $sobrenome"

                    sessao.influencer = dadosCadastroNome?.influencer

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
                                val RegistroResponse = response.body()

                                if (RegistroResponse != null) {

                                    val sessao = Sessao

                                    guardarDados(sessao, nomeInteiro, username, RegistroResponse)

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
                mostrarErroMensagem("Não é possível prosseguir sem aceitar os termos")
            }
        }
    }

    private fun cadastroInfluencer(registroResponse: RegisterResponse) {
        val intent = Intent(this, CadastroInfluencer::class.java)
        intent.putExtra("dados", registroResponse.id)
        startActivity(intent)
    }

    private fun guardarDados(sessao: Sessao, nomeInteiro: String, username: String, registroResponse: RegisterResponse) {
        sessao.nome = nomeInteiro
        sessao.username = username
        sessao.idUsuario = registroResponse?.id.toString()

        // Offiline
        val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()

        // Adiciona dados chave-valor
        editor.putInt("idUsuario", registroResponse.id.toInt())
        editor.putString("nome", registroResponse.nome)
        editor.putString("username", registroResponse.username)
        editor.putBoolean("influencer", registroResponse.influencer)


        // Salva as mudanças
        editor.apply()
    }

    private fun perfilUsuarioComum() {
        val intent = Intent(this, PerfilUsuarioComum::class.java)
        startActivity(intent)
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}