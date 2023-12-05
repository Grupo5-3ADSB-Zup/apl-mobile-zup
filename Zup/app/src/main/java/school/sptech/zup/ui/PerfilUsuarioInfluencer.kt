package school.sptech.zup.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.R
import school.sptech.zup.data.model.FotoResponse
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityFormularioPerfil4Binding
import school.sptech.zup.databinding.ActivityPerfilUsuarioInfluencerBinding
import school.sptech.zup.domain.model.DadosEnvioTelaInfluencer
import school.sptech.zup.domain.model.FotoRequest
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.network.ServiceProvider
import school.sptech.zup.presenter.feed.Feed
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

@Suppress("DEPRECATION")
class PerfilUsuarioInfluencer : AppCompatActivity() {

    private val binding by lazy {
        ActivityPerfilUsuarioInfluencerBinding.inflate(layoutInflater)
    }
    private val PICK_IMAGE = 1
    val sessao = Sessao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dadosTelaInfluenciadoresPerfil =
            intent.getSerializableExtra("dados") as? DadosEnvioTelaInfluencer

        val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

        val valorNome = sharedPreferences.getString("nome", null)
        val valorLinkInsta = sharedPreferences.getString("linkInstagram", null)
        val valorLinkYoutube = sharedPreferences.getString("linkYoutube", null)
        val valorLinkTikTok = sharedPreferences.getString("linkTikTok", null)
        val valorFoto = sharedPreferences.getString("foto", null)
        val valorInfluencer = sharedPreferences.getBoolean("influencer", false)

        if (sessao.nome != "" && dadosTelaInfluenciadoresPerfil == null) {
            binding.nomeUsuario.text = sessao.nome
            //binding.instagramUsuario = sessao.linkInstagram
            //binding.youtubeUsuario = sessao.linkYoutube
            //binding.tiktokUsuario = sessao.linkTikTok

            if (sessao.foto != "") {
                colocarFotoUsuario(sessao.foto)
            }
        } else if (valorNome != null && dadosTelaInfluenciadoresPerfil == null) {
            binding.nomeUsuario.text = valorNome
            //binding.instagramUsuario = valorLinkInsta
            //binding.youtubeUsuario = valorLinkYoutube
            //binding.tiktokUsuario = valorLinkTikTok

            if (valorFoto != null) {
                colocarFotoUsuario(valorFoto)
            }
        } else {
            if (dadosTelaInfluenciadoresPerfil != null) {
                binding.nomeUsuario.text = dadosTelaInfluenciadoresPerfil.nome
                //binding.instagramUsuario = dadosTelaInfluenciadoresPerfil.linkInstagram
                //binding.youtubeUsuario = dadosTelaInfluenciadoresPerfil.linkYoutube
                //binding.tiktokUsuario = dadosTelaInfluenciadoresPerfil.linkTikTok

                if (dadosTelaInfluenciadoresPerfil.foto != null) {
                    colocarFotoUsuario(dadosTelaInfluenciadoresPerfil.foto)
                }

                val imageView: ImageView = findViewById(R.id.editar_foto)
                imageView.visibility = View.GONE
            }
        }

        binding.editarFoto.setOnClickListener {
            abrirGaleria()
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

        menuItemSettings.setOnMenuItemClickListener {
            val intent = Intent(this, TelaConfiguracao2::class.java)
            startActivity(intent)
            true
        }

        menuItemPerfil.setOnMenuItemClickListener {
            val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

            val valorInfluencer = sharedPreferences.getBoolean("influencer", false)

            if (sessao.influencer == true || valorInfluencer == true) {
                val intent = Intent(this, PerfilUsuarioInfluencer::class.java)
                startActivity(intent)
                true
            } else {
                val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
                startActivity(intent)
                true
            }
        }
        var linkInsta = ""
        var linkYoutube = ""
        var linkTikTok = ""


        if(sessao.nome != "" && dadosTelaInfluenciadoresPerfil == null){
            linkInsta = "https://" + sessao.linkInstagram.toString()
            linkYoutube = "https://" + sessao.linkYoutube.toString()
            linkTikTok = "https://" + sessao.linkTikTok.toString()

        }else if (valorNome != null && dadosTelaInfluenciadoresPerfil == null){
            linkInsta = "https://" + valorLinkInsta
            linkYoutube = "https://" + valorLinkYoutube
            linkTikTok = "https://" + valorLinkTikTok
        }  else {
            linkInsta = "https://" + dadosTelaInfluenciadoresPerfil?.linkInstagram
            linkYoutube = "https://" + dadosTelaInfluenciadoresPerfil?.linkYoutube
            linkTikTok = "https://" + dadosTelaInfluenciadoresPerfil?.linkTikTok
        }


        binding.instagramUsuario.setOnClickListener {
            abrirLinkSocial(linkInsta)
        }

        binding.youtubeUsuario.setOnClickListener {
            abrirLinkSocial(linkYoutube)
        }

        binding.tiktokUsuario.setOnClickListener {
            abrirLinkSocial(linkTikTok)
        }
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            // A imagem foi escolhida com sucesso
            val selectedImageUri = data.data
            try {

                val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)
                val valorIdUsario = sharedPreferences.getLong("idUsuario", 0)


                // Convertendo a imagem para bytes
                val inputStream: InputStream? = contentResolver.openInputStream(selectedImageUri!!)
                val bytes = readBytes(inputStream)

                val dados = FotoRequest(
                    foto = bytes
                )
                println("USUARIOOO  $valorIdUsario")
                val envioApi = ServiceProvider.service.adicionarImagem(valorIdUsario, dados)
                envioApi.enqueue(object : Callback<FotoResponse> {
                    override fun onResponse(
                        call: Call<FotoResponse>, response: Response<FotoResponse>
                    ) {
                        if (response.isSuccessful) {

                            if (response.body() != null) {
                                colocarFotoUsuario(response.body().foto)
                            }

                        } else {
                            mostrarErroMensagem("Erro na solicitação")
                        }
                    }

                    override fun onFailure(call: Call<FotoResponse>?, t: Throwable?) {
                        if (t != null) {
                            mostrarErroMensagem("Erro na rede: ${t.message}")
                            println("ERROOOOOOOOOOOOOOOOOOOOO  ${t.message}")
                        }
                    }
                })

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun colocarFotoUsuario(foto: String?) {
        val imageView: ImageView = binding.fotoUsuario

        val decodedBytes: ByteArray = Base64.decode(foto, Base64.DEFAULT)
        val bitmap: Bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        imageView.setImageBitmap(bitmap)
    }

    @Throws(IOException::class)
    private fun readBytes(inputStream: InputStream?): ByteArray {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)

        var len: Int
        while (inputStream!!.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }

        return byteBuffer.toByteArray()
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun abrirLinkSocial(link: String) {
        if (link.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)
        } else {
            Toast.makeText(this, "Link não disponível", Toast.LENGTH_SHORT).show()
        }
    }
}