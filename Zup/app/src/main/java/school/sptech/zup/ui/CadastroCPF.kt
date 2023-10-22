package school.sptech.zup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import school.sptech.zup.rest.RetrofitService

class CadastroCPF : AppCompatActivity() {

    private val retrofitService: RetrofitService = TODO();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_cpf)
    }

    fun saveEvent(addRequest: EventoRegistroResquest, onResponse: (EventoRegistroResponse?, Throwable?) -> Unit){
        val body = Gson().toJson(addRequest)
            .toRequestBody("application/json; charset=UTF-8".toMediaType())

        retrofitService.saveEvent(body).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response?.isSuccessful!!){
                    val message = response.body()?.string()
                    val res = Gson().fromJson(message, EventoRegistroResponse::class.java)
                    onResponse(res, null)
                }else{
                    val message = response.errorBody()?.string()
                    val res = Gson().fromJson(message, EventoRegistroResponse::class.java)
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                onResponse(null, t)
            }

        })
    }
}