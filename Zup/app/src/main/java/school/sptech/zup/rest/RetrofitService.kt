package school.sptech.zup.rest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import school.sptech.zup.model.EventoRegistro

interface RetrofitService {

    @POST("")
    fun saveEvent(@Body event: EventoRegistro): Call<EventoRegistro>
}