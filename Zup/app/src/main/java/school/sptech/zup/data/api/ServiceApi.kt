package school.sptech.zup.data.api

import school.sptech.zup.domain.model.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import school.sptech.zup.data.model.CalculoPesoPorNoticiaIAResponse
import school.sptech.zup.data.model.ComentarioResponse
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.data.model.FotoResponse
import school.sptech.zup.data.model.GptResponse
import school.sptech.zup.data.model.PerfilUsuarioResponse
import school.sptech.zup.data.model.RegisterResponse
import school.sptech.zup.data.model.UsuarioResponse
import school.sptech.zup.data.model.response.LoginResponse
import school.sptech.zup.domain.model.CadastroDadosInfluencerRequest
import school.sptech.zup.domain.model.ComentarioRequest
import school.sptech.zup.domain.model.DadosEnvioApiFormularioPerfil
import school.sptech.zup.domain.model.FotoRequest
import school.sptech.zup.domain.model.GptRequest
import school.sptech.zup.domain.model.RegisterRequest

public interface ServiceApi {

    @GET("noticia/rss")
    fun getFeed(): Call<List<FeedResponse>>

    @POST("cadastro/user/comum")
    fun saveUser(@Body registerUser: RegisterRequest): Call<RegisterResponse>

    @POST("login/logar")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("mobile/usuarios/perfil")
    fun SalvarPerfilUsuario(@Body perfilRequest: DadosEnvioApiFormularioPerfil): Call<PerfilUsuarioResponse>

    @GET("mobile/usuarios/perfil/influenciadores")
    fun BuscaTodosUsuariosInfluencers(): Call<List<PerfilUsuarioResponse>>

    @POST("noticia/rss/info")
    fun InserirNoticiasGPT(@Body gptRequest: GptRequest): Call<GptResponse>

    @PATCH("usuario/foto/{idUsuario}")
    fun adicionarImagem(@Path("idUsuario") idUsuario: Long, @Body foto: FotoRequest) : Call<FotoResponse>

    @POST("usuario/influencer/dados/cadastro")
    fun cadastroInfluencerDados(@Body cadastroDadosInfluencerRequest : CadastroDadosInfluencerRequest) :Call<Boolean>

    @GET("mobile/usuarios/perfil/{IdPerfil}")
    fun BuscaUsuariosInfluencerTpPerfil(@Path("IdPerfil") idPerfil: Long): Call<List<PerfilUsuarioResponse>>

    @GET("usuario/{idUsuario}")
    fun getUsuarioId(@Path("idUsuario") idUsuario: Long): Call<UsuarioResponse>

    @GET("IA/pesos/analise/porcentagem/{idNoticia}")
    fun GetPorcentagemPeso(@Path("idNoticia") idNoticia: Integer?) : Call<CalculoPesoPorNoticiaIAResponse>

    @POST("noticia/comentarios/{idUsuario}/{idNoticia}")
    fun adicionarComentario(@Body comentarioRequest: ComentarioRequest,
                            @Path("idUsuario") idUsuario: Long,
                            @Path("idNoticia") idNoticia: Long?): Call<ComentarioResponse>

    @GET("noticia/comentarios-id-noticia/{idNoticia}")
    fun getComentarioMobile(@Path("idNoticia") idNoticia: Integer?): Call<List<ComentarioResponse>>

}