package school.sptech.zup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.data.model.PerfilUsuarioResponse
import school.sptech.zup.databinding.ActivityBuscarInfluenciadoresBinding
import school.sptech.zup.network.ServiceProvider

class BuscarInfluenciadores : AppCompatActivity() {

    val binding by lazy {
        ActivityBuscarInfluenciadoresBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val call = ServiceProvider.service.BuscaTodosUsuariosInfluencers()

        call.enqueue(object : Callback<List<PerfilUsuarioResponse>> {
            override fun onResponse(
                call: Call<List<PerfilUsuarioResponse>>,
                response: Response<List<PerfilUsuarioResponse>>
            ) {
                if (response.isSuccessful) {
                    val perfilResponse = response.body()

                    // Chamar Sessão e Preencher com os dados do Usuário

                    if (perfilResponse != null) {

                        // Falar Matheus

                    } else {
                        mostrarErroMensagem("Credenciais inválidas")
                    }
                } else {
                    mostrarErroMensagem("Erro na solicitação")
                }
            }
            override fun onFailure(call: Call<List<PerfilUsuarioResponse>>, t: Throwable) {
                mostrarErroMensagem("Erro na rede: ${t.message}")
            }
        })
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
