package school.sptech.zup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import school.sptech.zup.R
import school.sptech.zup.data.api.ServiceApi
import school.sptech.zup.data.model.response.InfluenciadoresResponse
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.databinding.ActivityBuscarInfluenciadoresBinding
import school.sptech.zup.databinding.ActivityOnboarding1Binding
import java.io.IOException

class BuscarInfluenciadores : AppCompatActivity() {

    val binding by lazy {

        ActivityBuscarInfluenciadoresBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val get = ServiceApi.buscarInfluenciadores()

        ServiceApi.client.newCall(get).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()

                    val gson = Gson()
                    val influenciadoresResponse =
                        gson.fromJson(responseBody, InfluenciadoresResponse::class.java)

                    val dados = influenciadoresResponse

                    if (dados != null) {
                        binding.nomeInfluenciador.text = dados.nome.toString()
//                        binding.fotoInfluenciador.text = dados.foto.toString()
                    }


                } else {
                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }


}
