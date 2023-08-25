package com.example.zup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    fun avancarCadastro(view: View) {
        val intent = Intent(this, Cadastro2Activity::class.java)
        startActivity(intent)
    }
}
