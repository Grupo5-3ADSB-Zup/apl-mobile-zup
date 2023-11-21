package school.sptech.zup.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.R
import school.sptech.zup.data.model.PerfilUsuarioResponse
import school.sptech.zup.databinding.ActivityFormularioPerfil5Binding
import school.sptech.zup.domain.model.DadosEnvioApiFormularioPerfil
import school.sptech.zup.domain.model.DadosTelaFormularioPerfil4Request
import school.sptech.zup.network.ServiceProvider.service
import school.sptech.zup.presenter.feed.Feed

@Suppress("DEPRECATION")
class FormularioPerfil5 : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormularioPerfil5Binding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonVoltar.setOnClickListener {
            telaFormulario4()
        }

        val dadosFormularioParte4 =
            intent.getSerializableExtra("dados") as? DadosTelaFormularioPerfil4Request

        val radioGroup = findViewById<RadioGroup>(R.id.meuRadioGroup)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            var selecao = ""
            when (checkedId) {
                R.id.meu_RadioButton_valor1 -> {
                    val radioButton1 = findViewById<RadioButton>(R.id.meu_RadioButton_valor1)
                    val textoRadioButton1 = radioButton1.text.toString()
                    selecao = textoRadioButton1
                }

                R.id.meu_RadioButton_valor2 -> {
                    val radioButton2 = findViewById<RadioButton>(R.id.meu_RadioButton_valor2)
                    val textoRadioButton2 = radioButton2.text.toString()
                    selecao = textoRadioButton2
                }

                R.id.meu_RadioButton_valor3 -> {
                    val radioButton3 = findViewById<RadioButton>(R.id.meu_RadioButton_valor3)
                    val textoRadioButton3 = radioButton3.text.toString()
                    selecao = textoRadioButton3
                }

                else -> {
                }
            }

            val perfilRequest = DadosEnvioApiFormularioPerfil(
                idUsuario = dadosFormularioParte4?.idUsuario.toString(),
                tela1 = dadosFormularioParte4?.radioButtonTelaFormulario1.toString(),
                tela2 = dadosFormularioParte4?.radioButtonTelaFormulario2.toString(),
                tela3 = dadosFormularioParte4?.radioButtonTelaFormulario4.toString(),
                tela4 = dadosFormularioParte4?.radioButtonTelaFormulario4.toString(),
                tela5 = selecao
            )

            binding.buttonConcluir.setOnClickListener {
                envioBaseDados(perfilRequest)
            }
        }

        val botaoNavBar = binding.navBar

        val menuItemHome = botaoNavBar.menu.findItem(R.id.botao_home)
        val menuItemPesquisar = botaoNavBar.menu.findItem(R.id.botao_search)
        val menuItemSettings = botaoNavBar.menu.findItem(R.id.botao_settings)
        val menuItemPerfil = botaoNavBar.menu.findItem(R.id.botao_profile)

        menuItemHome.setOnMenuItemClickListener {
            val intent = Intent(this, Feed::class.java)
            startActivity(intent)
            true
        }

        menuItemPesquisar.setOnMenuItemClickListener {
            val intent = Intent(this, BuscarInfluenciadores::class.java)
            startActivity(intent)
            true
        }

        menuItemSettings.setOnMenuItemClickListener {
            val intent = Intent(this, TelaConfiguracoes::class.java)
            startActivity(intent)
            true
        }

        menuItemPerfil.setOnMenuItemClickListener {
            val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
            startActivity(intent)
            true
        }
    }

    private fun telaFormulario4() {
        val intent = Intent(this, FormularioPerfil4::class.java)
        startActivity(intent)
    }

    private fun envioBaseDados(perfilRequest: DadosEnvioApiFormularioPerfil) {

        val call = service.SalvarPerfilUsuario(perfilRequest)

        call.enqueue(object : Callback<PerfilUsuarioResponse> {
            override fun onResponse(
                call: Call<PerfilUsuarioResponse>, response: Response<PerfilUsuarioResponse>
            ) {
                if (response.isSuccessful) {
                    val perfilResponse = response.body()

                    // Chamar Sessão e Preencher com os dados do Usuário

                    if (perfilResponse != null) {

                        iniciarFeed()
                    } else {
                        mostrarErroMensagem("Credenciais inválidas")
                    }
                } else {
                    mostrarErroMensagem("Erro na solicitação")
                }
            }

            override fun onFailure(call: Call<PerfilUsuarioResponse>, t: Throwable) {
                mostrarErroMensagem("Erro na rede: ${t.message}")
            }
        })
    }

    private fun iniciarFeed() {
        val intent = Intent(this, Feed::class.java)
        startActivity(intent)
    }

    private fun mostrarErroMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}