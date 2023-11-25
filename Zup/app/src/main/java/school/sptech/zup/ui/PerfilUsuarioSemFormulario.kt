package school.sptech.zup.ui

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.R
import school.sptech.zup.data.model.FotoResponse
import school.sptech.zup.data.model.GptResponse
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityPerfilUsuarioSemFormularioBinding
import school.sptech.zup.domain.model.FotoRequest
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.network.ServiceProvider.service
import school.sptech.zup.presenter.feed.Feed
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

@Suppress("DEPRECATION")
class PerfilUsuarioSemFormulario : AppCompatActivity() {

    val binding by lazy {
        ActivityPerfilUsuarioSemFormularioBinding.inflate(layoutInflater)
    }

    private val PICK_IMAGE = 1

    val sessao = Sessao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.nomeUsuario.text = sessao?.nome.toString()
        binding.usernameUsuario.text = sessao?.username.toString()

        binding.editarFoto.setOnClickListener{
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
            val intent = Intent(this, BuscarInfluenciadores::class.java)
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

        menuItemPerfil.setOnMenuItemClickListener {
            val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
            startActivity(intent)
            true
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
                // Convertendo a imagem para bytes
                val inputStream: InputStream? = contentResolver.openInputStream(selectedImageUri!!)
                val bytes = readBytes(inputStream)

                val dados = FotoRequest(
                    foto = bytes
                )
                val envioApi = service.adicionarImagem(sessao.idUsuario.toLong(),dados)
                envioApi.enqueue(object : Callback<FotoResponse> {
                    override fun onResponse(
                        call: Call<FotoResponse>, response: Response<FotoResponse>
                    ) {
                        if (response.isSuccessful) {
                            val fotoResponse = response.body()

                            if (fotoResponse != null) {

                                val bitmap = BitmapFactory.decodeByteArray(fotoResponse.foto, 0, fotoResponse.foto.size)

                                val imageView: ImageView = binding.fotoPerfil

                                imageView.setImageBitmap(bitmap)

                            } else {
                                mostrarErroMensagem("Credenciais inválidas")
                            }
                        } else {
                            mostrarErroMensagem("Erro na solicitação")
                        }
                    }

                    override fun onFailure(call: Call<FotoResponse>?, t: Throwable?) {
                        if (t != null) {
                            mostrarErroMensagem("Erro na rede: ${t.message}")
                        }
                    }
                })

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
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
}