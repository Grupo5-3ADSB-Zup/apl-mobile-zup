package school.sptech.zup.presenter.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import school.sptech.zup.R
import school.sptech.zup.TelaInicial
import school.sptech.zup.data.model.RegisterResponse
import school.sptech.zup.databinding.ActivityCadastroCpfBinding
import school.sptech.zup.databinding.ActivityCadastroNomeBinding
import school.sptech.zup.databinding.ActivityCadastroSenhaBinding
import school.sptech.zup.di.DataModule
import school.sptech.zup.domain.model.Register
import school.sptech.zup.network.ServiceProvider
import school.sptech.zup.ui.PerfilUsuarioSemFormulario


class CadastroCPF : AppCompatActivity() {

    private val apiService = DataModule().providesServiceApi(ServiceProvider.createService(ServiceProvider::class.java))

    val binding by lazy {

        ActivityCadastroCpfBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)





        binding.buttonContinuar.setOnClickListener {

            val PerfilUsuarioSemFormulario = Intent(this, PerfilUsuarioSemFormulario::class.java)

            startActivity(PerfilUsuarioSemFormulario)


            val registroUser = Register(
                1,
                "Matheus",
                "matheusss@gmail.com",
                "matheus",
                "123456",
                true,
                "50855621869",
                "",
                1,
                1
            )

            val call = apiService.saveUser(registroUser)
            call.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.isSuccessful) {
                        // Sucesso, faça algo com a resposta do servidor
                        val resposta = response.body()
                    } else {
                        // Trate erros aqui
                        Log.e("TAG", "Erro na requisição POST: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    // Trate erros de rede aqui
                    Log.e("TAG", "Erro na requisição POST: ${t.message}")
                }
            })

        }
    }
}