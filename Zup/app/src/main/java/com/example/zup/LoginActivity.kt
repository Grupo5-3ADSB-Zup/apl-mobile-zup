package com.example.zup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var loginCadastrar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.loginButton)
        loginCadastrar = findViewById(R.id.cadastrarButton)


        loginButton.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                if (username == "joao" && password == "123") {
//                    COLOQUEI ESSAS CRENDENCIAIS P TESTAR E COLOQUEI QUANDO ELE LOGAR TROCAR DE TELA E IR P
//                    CADASTRO, SÓ P TESTE
                    Toast.makeText(this, "Login bem-sucedido", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Cadastro2Activity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
                }
            }
        }

        loginCadastrar.setOnClickListener {
            val intent = Intent(this, Cadastro2Activity::class.java)
            startActivity(intent)
        }


    }
}
