package school.sptech.zup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.R
import school.sptech.zup.data.model.RegisterResponse
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityCadastroCpfBinding
import school.sptech.zup.databinding.ActivityCadastroInfluencerBinding
import school.sptech.zup.domain.model.CadastroDadosInfluencerRequest
import school.sptech.zup.network.ServiceProvider
import school.sptech.zup.presenter.feed.Feed

@Suppress("DEPRECATION")
class CadastroInfluencer : AppCompatActivity() {

    val binding by lazy {
        ActivityCadastroInfluencerBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val IdUsuario = intent.getSerializableExtra("dados") as? Integer

        binding.buttonContinuar.setOnClickListener{
            val tiktok = binding.tkEditText.text.toString()
            val youtube = binding.ytEditText.text.toString()
            val instagram = binding.instaEditText.text.toString()


            val dadosEnvio = CadastroDadosInfluencerRequest(
                idUsuario = IdUsuario,
                tiktok = tiktok,
                youtube = youtube,
                instagram = instagram
            )

            val call = ServiceProvider.service.cadastroInfluencerDados(dadosEnvio)
            call.enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>?, response: Response<Boolean>?) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            val loginResponse = response?.body()

                            if (loginResponse != null) {
                                IniciarFeed()
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

    private fun IniciarFeed() {
        val intent = Intent(this, Feed::class.java)
        startActivity(intent)
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}