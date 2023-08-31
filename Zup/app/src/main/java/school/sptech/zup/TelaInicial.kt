package school.sptech.zup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TelaInicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
    }

    fun avancarCadastro(view: View) {
        val intent = Intent(this, TelaCadastro1::class.java)
        startActivity(intent)
    }

    fun avancarLogin(view: View) {
        val intent = Intent(this, LoginVelho::class.java)
        startActivity(intent)
    }
}