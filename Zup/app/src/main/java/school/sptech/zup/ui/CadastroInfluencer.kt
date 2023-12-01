package school.sptech.zup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.databinding.ActivityCadastroInfluencerBinding
import school.sptech.zup.domain.model.CadastroDadosInfluencerRequest
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.network.ServiceProvider
import school.sptech.zup.presenter.feed.Feed
import school.sptech.zup.presenter.login.Login

@Suppress("DEPRECATION")
class CadastroInfluencer : AppCompatActivity() {

    val binding by lazy {
        ActivityCadastroInfluencerBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sessao = Sessao

        val IdUsuario = intent.getSerializableExtra("dados") as? Integer

        val idUsuarioSessao = sessao.idUsuario.toInt()

        binding.buttonContinuar.setOnClickListener{
            val tiktok = binding.tkEditText.text.toString()
            val youtube = binding.ytEditText.text.toString()
            val instagram = binding.instaEditText.text.toString()


            val dadosEnvio = CadastroDadosInfluencerRequest(
                idUsuario = idUsuarioSessao,
                tiktok = tiktok,
                youtube = youtube,
                instagram = instagram
            )

            val call = ServiceProvider.service.cadastroInfluencerDados(dadosEnvio)
            call.enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>?, response: Response<Boolean>?) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            val cadastroInfluencer = response?.body()

                            if (cadastroInfluencer != null) {
                                IniciarLogin()
                            } else {
                                mostrarErroMensagem("Credenciais inválidas")
                            }
                        } else {
                            mostrarErroMensagem("Erro na solicitação")
                        }
                    }
                }

                override fun onFailure(call: Call<Boolean>?, t: Throwable?) {
                    if (t != null) {
                        mostrarErroMensagem("Erro na rede: ${t.message}")
                    }
                }
            })
        }
    }

    private fun IniciarLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}