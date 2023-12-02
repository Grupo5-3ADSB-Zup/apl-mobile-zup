package school.sptech.zup.ui

import android.content.Context
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
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.network.ServiceProvider
import school.sptech.zup.presenter.feed.Feed

class BuscarInfluenciadores : AppCompatActivity() {

    val binding by lazy {
        ActivityBuscarInfluenciadoresBinding.inflate(layoutInflater)
    }

    val sessao = Sessao

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

        menuItemHome.setOnMenuItemClickListener {
            val intent = Intent(this, Feed::class.java)
            startActivity(intent)
            true
        }

        menuItemPesquisar.setOnMenuItemClickListener {
            val intent = Intent(this, FiltroPerfil::class.java)
            startActivity(intent)
            true
        }

        menuItemSettings.setOnMenuItemClickListener{
            val intent = Intent(this, TelaConfiguracao2::class.java)
            startActivity(intent)
            true
        }

        menuItemPerfil.setOnMenuItemClickListener {
            val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

            val valorInfluencer = sharedPreferences.getBoolean("influencer", false)

            if(sessao.influencer == true || valorInfluencer == true){
                val intent = Intent(this, PerfilUsuarioInfluencer::class.java)
                startActivity(intent)
                true
            }else {
                val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
                startActivity(intent)
                true
            }
        }
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
