package school.sptech.zup.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.R
import school.sptech.zup.data.model.CalculoPesoPorNoticiaIAResponse
import school.sptech.zup.data.model.ComentarioResponse
import school.sptech.zup.databinding.ActivityTelaExibicaoDadosMlBinding
import school.sptech.zup.domain.model.ComentarioRequest
import school.sptech.zup.domain.model.DadosEnvioTelaMlRequest
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.network.ServiceProvider.service
import school.sptech.zup.presenter.feed.Feed
import school.sptech.zup.presenter.list.adapter.ItemPerfilUsuarioAdapter


class TelaExibicaoDadosMl : AppCompatActivity() {

    private val binding by lazy {
        ActivityTelaExibicaoDadosMlBinding.inflate(layoutInflater)
    }

    private val sessao = Sessao
    private var comentarioResponse: List<ComentarioResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dadosFeed = intent.getSerializableExtra("dados") as? DadosEnvioTelaMlRequest

        binding.PostEmissora.text = dadosFeed?.emissora.orEmpty()
        binding.PostTitulo.text = dadosFeed?.titulo.orEmpty()

        val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)
        val valorIdUsuario = sharedPreferences.getLong("idUsuario", 0)

        dadosFeed?.fotoNoticia?.let {
            Glide.with(this@TelaExibicaoDadosMl)
                .load(it)
                .into(binding.PostImage)
            Log.d("FeedAdapter", "Loading image at position ${dadosFeed.id}. Image URL: $it")
        }

        binding.PostDescricao.text = dadosFeed?.descricao.orEmpty()

        val call = service.GetPorcentagemPeso(dadosFeed?.id)
        call.enqueue(object : Callback<CalculoPesoPorNoticiaIAResponse> {
            override fun onResponse(
                call: Call<CalculoPesoPorNoticiaIAResponse>, response: Response<CalculoPesoPorNoticiaIAResponse>
            ) {
                if (response.isSuccessful) {
                    val pesos = response.body()

                    with(binding) {
                        porcentagemPesoCompra.text = pesos?.porcentagemPesoCompra?.toString() ?: "Sem dados"
                        porcentagemPesoPensaEmCompra.text = pesos?.porcentagemPesoPensaEmCompra?.toString() ?: "Sem dados"
                        porcentagemPesoNeutro.text = pesos?.porcentagemPesoNeutro?.toString() ?: "Sem dados"
                        porcentagemPesoPenseEmVender.text = pesos?.porcentagemPesoPenseEmVender?.toString() ?: "Sem dados"
                        porcentagemPesoVenda.text = pesos?.porcentagemPesoVenda?.toString() ?: "Sem dados"
                    }

                } else {
                    mostrarErroMensagem("Sem Comentários para essa notícia")
                    binding.divDadosML.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<CalculoPesoPorNoticiaIAResponse>?, t: Throwable?) {

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
                        exibirComentarios(comentarioResponse)
                    } else {
                        mostrarErroMensagem("Sem Comentários para essa notícia")
                    }
                } else {
                    mostrarErroMensagem("Erro na solicitação")
                }
            }

            override fun onFailure(call: Call<List<ComentarioResponse>>?, t: Throwable?) {
                mostrarErroMensagem("Erro na rede: ${t?.message}")
            }
        })

        binding.submitCommentButton.setOnClickListener {
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
                        val novoComentario = response.body()

                        if (novoComentario != null) {
                            // Adiciona o novo comentário à lista existente e exibe todos os comentários
                            val comentariosExist = mutableListOf<ComentarioResponse>()
                            comentariosExist.addAll(comentarioResponse ?: emptyList())
                            comentariosExist.add(novoComentario)
                            exibirComentarios(comentariosExist)
                            binding.commentInput.text.clear() // Limpa o campo de entrada após adicionar o comentário
                        } else {
                            mostrarErroMensagem("Erro ao adicionar comentário")
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
            val valorInfluencer = sharedPreferences.getBoolean("influencer", false)

            val intent = if(sessao.influencer == true || valorInfluencer == true) {
                Intent(this, PerfilUsuarioInfluencer::class.java)
            } else {
                Intent(this, PerfilUsuarioSemFormulario::class.java)
            }

            startActivity(intent)
            true
        }
    }

    private fun exibirComentarios(comentarios: List<ComentarioResponse>) {
        val comentariosOrdenados = comentarios.sortedByDescending { it.id }
        val comentariosText = comentariosOrdenados.joinToString("\n") { comentario ->
            "${colocarFotoUsuario(comentario.usuario.foto)} ${comentario.usuario.nome}: ${comentario.descricao}"
        }
        binding.commentList.text = comentariosText
    }

    private fun colocarFotoUsuario(foto: String): SpannableString {
        val spannableString = SpannableString(" ")
        val imageSpan = ImageSpan(getBitmapFromBase64(foto), ImageSpan.ALIGN_BASELINE)
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }

    private fun getBitmapFromBase64(base64: String): Bitmap {
        val decodedBytes: ByteArray = Base64.decode(base64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }


    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
