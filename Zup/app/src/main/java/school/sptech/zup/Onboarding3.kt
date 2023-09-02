package school.sptech.zup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Onboarding3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding3)
    }

    fun pularOnboarding(view: View) {
        val intent = Intent(this, CadastroNome::class.java)
        startActivity(intent)
    }

}