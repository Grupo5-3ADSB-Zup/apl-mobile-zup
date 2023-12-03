package school.sptech.zup.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import school.sptech.zup.data.model.ComentarioResponse
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.domain.model.ComentarioRequest
import school.sptech.zup.presenter.list.adapter.ComentarioAdapter

@Suppress("DEPRECATION")
class TelaExibicaoDadosMl : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ComentarioAdapter

    private val binding by lazy {
        ActivityTelaExibicaoDadosMlBinding.inflate(layoutInflater)
    }

    val sessao = Sessao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerViewFeed)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ComentarioAdapter(emptyList())
        recyclerView.adapter = adapter

        val dadosFeed = intent.getSerializableExtra("dados") as? DadosEnvioTelaMlRequest

        binding.PostEmissora.text = dadosFeed?.emissora.toString()
        binding.PostTitulo.text = dadosFeed?.titulo.toString()

        val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

        val valorIdUsuario = sharedPreferences.getLong("idUsuario", 0)

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

        val callGetComentario = service.getComentarioMobile(dadosFeed?.id)
        callGetComentario.enqueue(object : Callback<List<ComentarioResponse>> {
            override fun onResponse(
                call: Call<List<ComentarioResponse>>,
                response: Response<List<ComentarioResponse>>
            ) {
                if (response.isSuccessful) {
                    val comentarioResponse = response.body()

                    if (comentarioResponse != null) {
                        adapter.updateData(comentarioResponse)
                    } else {
                        mostrarErroMensagem("Credenciais inválidas")
                    }
                } else {
                    mostrarErroMensagem("Erro na solicitação")
                }
            }

            override fun onFailure(call: Call<List<ComentarioResponse>>?, t: Throwable?) {
                mostrarErroMensagem("Erro na rede: ${t?.message}")
            }
        })
        binding.submitCommentButton.setOnClickListener{

            val comentario = binding.commentInput.text.toString()

            val comentarioEnvio = ComentarioRequest(
                comentario = comentario
            )

            val callAdicionarComentario = service.adicionarComentario(comentarioEnvio, valorIdUsuario, dadosFeed?.id?.toLong())
            callAdicionarComentario.enqueue(object : Callback<ComentarioResponse> {
                override fun onResponse(
                    call: Call<ComentarioResponse>,
                    response: Response<ComentarioResponse>
                ) {
                    if (response.isSuccessful) {
                        val comentarioResponse = response.body()

                        if (comentarioResponse != null) {

                           val callGetComentario2 = service.getComentarioMobile(dadosFeed?.id)
                            callGetComentario2.enqueue(object : Callback<List<ComentarioResponse>> {
                                override fun onResponse(
                                    call: Call<List<ComentarioResponse>>,
                                    response: Response<List<ComentarioResponse>>
                                ) {
                                    if (response.isSuccessful) {
                                        val comentarioResponse = response.body()

                                        if (comentarioResponse != null) {
                                            adapter.updateData(comentarioResponse)
                                        } else {
                                            mostrarErroMensagem("Credenciais inválidas")
                                        }
                                    } else {
                                        mostrarErroMensagem("Erro na solicitação")
                                    }
                                }

                                override fun onFailure(call: Call<List<ComentarioResponse>>?, t: Throwable?) {
                                    mostrarErroMensagem("Erro na rede: ${t?.message}")
                                }
                            })

                        } else {
                            mostrarErroMensagem("Credenciais inválidas")
                        }
                    } else {
                        mostrarErroMensagem("Erro na solicitação")
                    }
                }

                override fun onFailure(call: Call<ComentarioResponse>?, t: Throwable?) {
                    mostrarErroMensagem("Erro na rede: ${t?.message}")
                }
            })
        }

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