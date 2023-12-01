package school.sptech.zup.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.ImageView
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityFormularioPerfil4Binding
import school.sptech.zup.databinding.ActivityPerfilUsuarioInfluencerBinding
import school.sptech.zup.domain.model.DadosEnvioTelaInfluencer
import school.sptech.zup.presenter.feed.Feed

@Suppress("DEPRECATION")
class PerfilUsuarioInfluencer : AppCompatActivity() {

    private val binding by lazy {
        ActivityPerfilUsuarioInfluencerBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dadosTelaInfluenciadoresPerfil = intent.getSerializableExtra("dados") as? DadosEnvioTelaInfluencer

        if (dadosTelaInfluenciadoresPerfil != null){
            binding.nomeUsuario.text = dadosTelaInfluenciadoresPerfil.nome.toString()
            binding.instagramUsuario.text = dadosTelaInfluenciadoresPerfil.linkInstagram.toString()
            binding.youtubeUsuario.text = dadosTelaInfluenciadoresPerfil.linkYoutube.toString()
            binding.tiktokUsuario.text = dadosTelaInfluenciadoresPerfil.linkTikTok.toString()

            if (dadosTelaInfluenciadoresPerfil.foto != null){
                colocarFotoUsuario(dadosTelaInfluenciadoresPerfil.foto)
            }
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
            val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
            startActivity(intent)
            true
        }
    }

    private fun colocarFotoUsuario(foto: String?) {
        val imageView: ImageView = binding.fotoUsuario

        val decodedBytes: ByteArray = Base64.decode(foto, Base64.DEFAULT)
        val bitmap: Bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        imageView.setImageBitmap(bitmap)
    }
}