package school.sptech.zup.data.model

data class RegisterResponse(
    val nome: String,
    val username: String?,
    val senha: String?,
    val cpf: String,
    val cnpj: String,
)
