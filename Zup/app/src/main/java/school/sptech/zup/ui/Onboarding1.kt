package school.sptech.zup.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import school.sptech.zup.R

class Onboarding1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding1)
    }

    fun pularOnboarding(view: View) {
        val intent = Intent(this, CadastroNome::class.java)
        startActivity(intent)
    }

    fun proximoOnboarding2(view: View) {
        val intent = Intent(this, Onboarding2::class.java)
        startActivity(intent)
    }



}