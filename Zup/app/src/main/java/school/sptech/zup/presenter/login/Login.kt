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
import school.sptech.zup.data.model.UsuarioResponse
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

                                guardarTodasAsInfosUsuarioPosCadastro(loginResponse.id)

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

     private fun guardarTodasAsInfosUsuarioPosCadastro(id: Long) {
        val call = service.getUsuarioId(id)
         call.enqueue(object : Callback<UsuarioResponse> {
             override fun onResponse(
                 call: Call<UsuarioResponse>, response: Response<UsuarioResponse>
             ) {
                 if (response.isSuccessful) {
                     val usuarioResponse = response.body()

                     if (usuarioResponse != null) {
                        adicionarGetShared(usuarioResponse, id)
                        adicionarSessao(usuarioResponse, id)
                     } else {
                         mostrarErroMensagem("Credenciais inválidas")
                     }
                 } else {
                     mostrarErroMensagem("Erro na solicitação")
                 }
             }

             override fun onFailure(call: Call<UsuarioResponse>?, t: Throwable?) {
                 mostrarErroMensagem("Erro na rede: ${t?.message}")
             }
         })
    }

    private fun adicionarSessao(usuarioResponse: UsuarioResponse, id: Long) {
        var sessao = Sessao

        sessao.idUsuario = id.toString()
        sessao.nome = usuarioResponse.nome
        sessao.email = null
        sessao.username = usuarioResponse.username
        sessao.influencer = usuarioResponse.influencer
        sessao.autenticaddo = usuarioResponse.autenticado
        sessao.cpf = usuarioResponse.cpf
        sessao.cnpj = null
        sessao.admin = 0
        sessao.idTpPerfil = usuarioResponse.idPerfil
        sessao.linkYoutube = usuarioResponse.linkYoutube
        sessao.linkInstagram = usuarioResponse.linkInstagram
        sessao.linkTikTok = usuarioResponse.linkTikTok
    }

    private fun adicionarGetShared(usuarioResponse: UsuarioResponse, id: Long) {
        val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

        // Cria um editor para modificar o SharedPreferences
        val editor = sharedPreferences.edit()

        // Adiciona dados chave-valor
        editor.putLong("idUsuario", id)
        editor.putString("nome", usuarioResponse.nome)
        editor.putString("email", usuarioResponse.nome)
        editor.putString("username", usuarioResponse.nome)
        editor.putString("senha", usuarioResponse.nome)
        editor.putString("influencer", usuarioResponse.nome)
        editor.putString("autenticado", usuarioResponse.nome)
        editor.putString("cpf", usuarioResponse.nome)
        editor.putString("cnpj", usuarioResponse.nome)
        editor.putString("admin", usuarioResponse.nome)
        editor.putString("idPerfil", usuarioResponse.nome)
        editor.putString("linkYoutube", usuarioResponse.nome)
        editor.putString("linkInstagram", usuarioResponse.nome)
        editor.putString("linkTikTok", usuarioResponse.nome)
        editor.putString("descPerfil", usuarioResponse.nome)
        editor.putString("foto", usuarioResponse.nome)


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
