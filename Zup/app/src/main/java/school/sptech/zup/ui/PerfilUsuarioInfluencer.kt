package school.sptech.zup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.R
import school.sptech.zup.databinding.ActivityFormularioPerfil4Binding
import school.sptech.zup.databinding.ActivityPerfilUsuarioInfluencerBinding
import school.sptech.zup.presenter.feed.Feed

class PerfilUsuarioInfluencer : AppCompatActivity() {

    private val binding by lazy {
        ActivityPerfilUsuarioInfluencerBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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

        menuItemSettings.setOnMenuItemClickListener {
            val intent = Intent(this, TelaConfiguracoes::class.java)
            startActivity(intent)
            true
        }

        menuItemPerfil.setOnMenuItemClickListener {
            val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
            startActivity(intent)
            true
        }
    }
}