package school.sptech.zup.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import school.sptech.zup.R
import school.sptech.zup.data.model.CalculoPesoPorNoticiaIAResponse
import school.sptech.zup.databinding.ActivityTelaExibicaoDadosMlBinding
import school.sptech.zup.domain.model.DadosEnvioTelaMlRequest
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.network.ServiceProvider.service
import school.sptech.zup.presenter.feed.Feed
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class TelaExibicaoDadosMl : AppCompatActivity() {

    private val binding by lazy {
        ActivityTelaExibicaoDadosMlBinding.inflate(layoutInflater)
    }

    val sessao = Sessao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dadosFeed = intent.getSerializableExtra("dados") as? DadosEnvioTelaMlRequest

        binding.PostEmissora.text = dadosFeed?.emissora.toString()
        binding.PostTitulo.text = dadosFeed?.titulo.toString()

        dadosFeed?.fotoNoticia?.let {
            Glide.with(this@TelaExibicaoDadosMl) // Use o contexto do itemView
                .load(it)
                .into(binding.PostImage)
            Log.d("FeedAdapter", "Loading image at position ${dadosFeed?.id}. Image URL: $it")
        }

        binding.PostDescricao.text = dadosFeed?.descricao.toString()


        val call = service.GetPorcentagemPeso(dadosFeed?.id)
        call.enqueue(object : Callback<CalculoPesoPorNoticiaIAResponse> {
            override fun onResponse(
                call: Call<CalculoPesoPorNoticiaIAResponse>, response: Response<CalculoPesoPorNoticiaIAResponse>
            ) {
                if (response.isSuccessful) {
                    val pesos = response.body()

                    binding.porcentagemPesoCompra.text = if(pesos.porcentagemPesoCompra.toString() == null) "Sem dados" else pesos.porcentagemPesoCompra.toString()
                    binding.porcentagemPesoPensaEmCompra.text = if(pesos.porcentagemPesoPensaEmCompra.toString() == null) "Sem dados" else pesos.porcentagemPesoPensaEmCompra.toString()
                    binding.porcentagemPesoNeutro.text = if(pesos.porcentagemPesoNeutro.toString() == null) "Sem dados" else pesos.porcentagemPesoNeutro.toString()
                    binding.porcentagemPesoPenseEmVender.text = if(pesos.porcentagemPesoPenseEmVender.toString() == null) "Sem dados" else pesos.porcentagemPesoPenseEmVender.toString()
                    binding.porcentagemPesoVenda.text = if(pesos.porcentagemPesoVenda.toString() == null) "Sem dados" else pesos.porcentagemPesoVenda.toString()

                } else {
                    mostrarErroMensagem("Sem Comentários para essa notícia")
                    binding.divDadosML.visibility = View.GONE
                }
            }
            override fun onFailure(call: Call<CalculoPesoPorNoticiaIAResponse>?, t: Throwable?) {
                // Lidar com falhas de rede
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

        menuItemPesquisar.setOnMenuItemClickListener{
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