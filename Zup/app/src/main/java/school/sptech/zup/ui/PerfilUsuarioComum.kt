package school.sptech.zup.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityLoginEmailBinding
import school.sptech.zup.databinding.ActivityPerfilUsuarioComumBinding
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil1Request
import school.sptech.zup.presenter.feed.Feed


@Suppress("DEPRECATION")
class PerfilUsuarioComum : AppCompatActivity() {

    private val binding by lazy {
        ActivityPerfilUsuarioComumBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dadosLogin = intent.getSerializableExtra("dados") as? LoginResponse
        if (dadosLogin != null) {
            binding.nomeUsuario.text = dadosLogin.nome.toString()
        }


        binding.buttonToFormularioPerfil.setOnClickListener{
            iniciarFormulario()
        }
    }

    private fun iniciarFormulario(){
        val intent = Intent(this, FormularioPerfil1::class.java)
        startActivity(intent)
    }
}