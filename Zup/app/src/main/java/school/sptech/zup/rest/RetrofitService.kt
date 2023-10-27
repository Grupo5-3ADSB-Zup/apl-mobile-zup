package school.sptech.zup.rest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import school.sptech.zup.model.EventoRegistro

interface RetrofitService {

    @POST("/cadastro/user/comum")
    fun saveEvent(@Body event: EventoRegistro): Call<ResponseBody>
}
