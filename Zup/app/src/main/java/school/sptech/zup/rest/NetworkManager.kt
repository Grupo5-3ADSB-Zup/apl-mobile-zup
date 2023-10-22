package school.sptech.zup.rest

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.model.EventoRegistroResponse
import school.sptech.zup.model.EventoRegistroResquest

class NetworkManager {

    private val retrofitService: RetrofitService = TODO()

    fun saveEvent(addRequest: EventoRegistroResquest, onResponse: (EventoRegistroResponse?, Throwable?) -> Unit) {
        val body = Gson().toJson(addRequest)
            .toRequestBody("application/json; charset=UTF-8".toMediaType())

        // Substitua 'retrofitService' pelo objeto RetrofitService real
        retrofitService.saveEvent(body).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response?.isSuccessful!!) {
                    val message = response.body()?.string()
                    val res = Gson().fromJson(message, EventoRegistroResponse::class.java)
                    onResponse(res, null)
                } else {
                    val message = response.errorBody()?.string()
                    val res = Gson().fromJson(message, EventoRegistroResponse::class.java)
                    onResponse(res, null)  // É importante chamar onResponse mesmo em caso de erro
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                onResponse(null, t)
            }
        })
    }
}
