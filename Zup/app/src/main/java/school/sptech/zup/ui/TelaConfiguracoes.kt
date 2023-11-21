package school.sptech.zup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.R
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityPerfilUsuarioComumBinding
import school.sptech.zup.databinding.ActivityTelaConfiguracoesBinding
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil1Request
import school.sptech.zup.domain.model.DadosTelaRefazerFormulario
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.presenter.register.CadastroNome

//@Suppress("DEPRECATION")
class TelaConfiguracoes : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaConfiguracoesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


       // val sessao = Sessao
       // val dadosLogin = intent.getSerializableExtra("dados") as? LoginResponse

        //val dados = DadosTelaRefazerFormulario(
        //    sessao?.idUsuario.toString()
        //)


        //binding.refazerTeste.setOnClickListener {
        //    formularioParte1(dados)
        //}

    }

    //private fun formularioParte1(dados: DadosTelaRefazerFormulario) {
    //    val intent = Intent(this, FormularioPerfil1::class.java)
    //    intent.putExtra("dados", dados)
    //    startActivity(intent)
    //}
}