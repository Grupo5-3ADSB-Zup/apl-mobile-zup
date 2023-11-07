package school.sptech.zup.domain.model

data class RegisterRequest(
    val nome: String,
    val username: String?,
    val senha: String?,
    val cpf: String,
    val cnpj: String,
)
