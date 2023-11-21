package school.sptech.zup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.R
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

        val botaoNavBar = binding.navBar

        val menuItemHome = botaoNavBar.menu.findItem(R.id.botao_home)
        val menuItemPesquisar = botaoNavBar.menu.findItem(R.id.botao_search)
        val menuItemSettings = botaoNavBar.menu.findItem(R.id.botao_settings)
        val menuItemPerfil = botaoNavBar.menu.findItem(R.id.botao_profile)

//        menuItemHome.setOnMenuItemClickListener {
//            call
//            true
//        }

        menuItemPesquisar.setOnMenuItemClickListener {
            val intent = Intent(this, BuscarInfluenciadores::class.java)
            startActivity(intent)
            true
        }

        menuItemSettings.setOnMenuItemClickListener {
            val intent = Intent(this, TelaConfiguracoes::class.java)
            startActivity(intent)
            true
        }

        menuItemPerfil.setOnMenuItemClickListener {
            val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
            startActivity(intent)
            true
        }

    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
