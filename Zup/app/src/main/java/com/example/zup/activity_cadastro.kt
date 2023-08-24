package com.example.zup

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class activity_cadastro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    fun avancarCadastro(view: View) {
        val intent = Intent(this, activity_cadastro2::class.java)
        startActivity(intent)
    }
}
