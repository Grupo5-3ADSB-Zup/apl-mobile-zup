package school.sptech.zup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Onboarding2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding2)
    }

    fun pularOnboarding(view: View) {
        val intent = Intent(this, CadastroNome::class.java)
        startActivity(intent)
    }

    fun proximoOnboarding3(view: View) {
        val intent = Intent(this, Onboarding3::class.java)
        startActivity(intent)
    }
}