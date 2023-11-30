package school.sptech.zup.domain.model

public object Sessao {
    var idUsuario: String = ""
    var nome: String = ""
    var username: String = ""
    var idTpPerfil: Long = 0
    var influencer: Boolean? = null
    var primeiroAcesso: Boolean = true
    var token: String = ""
}