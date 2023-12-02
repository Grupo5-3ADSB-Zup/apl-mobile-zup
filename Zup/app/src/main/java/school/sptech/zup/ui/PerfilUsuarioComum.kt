package school.sptech.zup.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityPerfilUsuarioComumBinding
import school.sptech.zup.domain.model.Sessao
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

        val sessao = Sessao

        var dadosLogin = intent.getSerializableExtra("dados") as? LoginResponse

        if (sessao.nome == null) {
            binding.nomeUsuario.text = dadosLogin?.nome.toString()
        } else binding.nomeUsuario.text = sessao?.nome.toString()

        binding.buttonToFormularioPerfil.setOnClickListener {

            iniciarFormulario(dadosLogin)
        }
    }

    private fun iniciarFormulario(dadosLogin: LoginResponse?) {
        val intent = Intent(this, FormularioPerfil1::class.java)
        intent.putExtra("dadosLogin", dadosLogin)
        startActivity(intent)
    }
}