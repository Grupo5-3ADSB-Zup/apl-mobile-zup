package school.sptech.zup.ui

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
import school.sptech.zup.databinding.ActivityCadastroCpfBinding
import school.sptech.zup.model.EventoRegistro
import school.sptech.zup.rest.RetrofitService

class CadastroCPF : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroCpfBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/") // Certifique-se de que esta URL seja apropriada
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(RetrofitService::class.java)

        binding.buttonContinuar.setOnClickListener {

            // Preparar os dados do registro do usuário
            val registroUser = EventoRegistro(
                "Matheus",
                "matheusss@gmail.com",
                "matheus",
                "123456",
                true,
                "50855621869",
                "", // Certifique-se de fornecer os dados apropriados
                1,
                foto = 1

            )

            // Enviar a solicitação de registro
            val call = apiService.saveEvent(registroUser)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        // Sucesso, faça algo com a resposta do servidor
                        val resposta = response.body()
                    } else {
                        // Trate erros aqui
                        Log.e("TAG", "Erro na requisição POST: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // Trate erros de rede aqui
                    Log.e("TAG", "Erro na requisição POST: ${t.message}")
                }
            })

            // Redirecionar para a próxima atividade
            val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
            startActivity(intent)
        }

        binding.buttonCancel.setOnClickListener {
            val intent = Intent(this, TelaInicial::class.java)
            startActivity(intent)
        }
    }
}
