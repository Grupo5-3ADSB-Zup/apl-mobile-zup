package school.sptech.zup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityTelaExibicaoDadosMlBinding
import school.sptech.zup.domain.model.DadosEnvioTelaMlRequest

@Suppress("DEPRECATION")
class TelaExibicaoDadosMl : AppCompatActivity() {

    private val binding by lazy {
        ActivityTelaExibicaoDadosMlBinding.inflate(layoutInflater)
    }

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
    }
}