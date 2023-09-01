package school.sptech.zup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TelaCadastro1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro1)
    }

    fun avancarCadastro(view: View) {
        val intent = Intent(this, TelaCadastro2::class.java)
        startActivity(intent)
    }
}