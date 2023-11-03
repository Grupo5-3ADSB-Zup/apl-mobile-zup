package school.sptech.zup.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import school.sptech.zup.R
import school.sptech.zup.databinding.ActivityFormularioPerfil3Binding
import school.sptech.zup.databinding.ActivityFormularioPerfil5Binding
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil1Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil2Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil3Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil4Request
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil5Request

class FormularioPerfil5 : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormularioPerfil5Binding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}