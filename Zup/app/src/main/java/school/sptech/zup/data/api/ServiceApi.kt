package school.sptech.zup.data.api

import school.sptech.zup.domain.model.LoginRequest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.data.model.PerfilUsuarioResponse
import school.sptech.zup.data.model.RegisterResponse
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.domain.model.DadosEnvioApiFormularioPerfil
import school.sptech.zup.domain.model.RegisterRequest

public interface ServiceApi {

    @GET("noticia/rss")
    fun getFeed(): Call<List<FeedResponse>>

    @POST("cadastro/user/comum")
    fun saveUser(@Body registerUser: RegisterRequest): Call<RegisterResponse>

    @POST("login/logar")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("mobile/usuarios/perfil")
    fun SalvarPerfilUsuario(@Body perfilRequest: DadosEnvioApiFormularioPerfil) : Call<PerfilUsuarioResponse>
}