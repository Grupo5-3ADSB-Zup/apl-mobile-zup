package school.sptech.zup.rest

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST

interface RetrofitService {

    @POST()
    fun saveEvent(requestBody: RequestBody) : Call<ResponseBody>
}