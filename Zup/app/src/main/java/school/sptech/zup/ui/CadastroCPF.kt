package school.sptech.zup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.R
import school.sptech.zup.model.EventoFeedRequest
import school.sptech.zup.model.EventoRegistroResponse
import school.sptech.zup.model.EventoRegistroResquest
import school.sptech.zup.rest.NetworkManager
import school.sptech.zup.rest.RetrofitService

class CadastroCPF : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_cpf)
    }

    private val networkManager = NetworkManager()
    fun onClickButtonContinuar(view: View) {
        val eventoRequest = EventoRegistroResquest()

        networkManager.saveEvent(eventoRequest) { response, error ->
            if (error != null) {
                Log.e("Erro na requisição", error.message ?: "Erro desconhecido")
            } else if (response != null) {
            } else {
            }
        }
    }
}
}