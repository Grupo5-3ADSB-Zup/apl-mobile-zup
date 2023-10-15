package school.sptech.zup.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import school.sptech.zup.R

class TelaInicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
    }

    fun entrarLogin(view: View) {
        val intent = Intent(this, LoginEmail::class.java)
        startActivity(intent)
    }

    fun entrarOnboarding(view: View) {
        val intent = Intent(this, Onboarding1::class.java)
        startActivity(intent)
    }


}