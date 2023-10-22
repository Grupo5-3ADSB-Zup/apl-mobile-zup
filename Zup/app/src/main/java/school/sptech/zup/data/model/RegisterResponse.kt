package school.sptech.zup.data.model

data class RegisterResponse(
    val nome: String,
    val email: String,
    val username: String,
    val senha: String,
    val influencer: Boolean,
    val cpf: String,
    val cnpj: String,
    val Admin: Int,
    val foto: Byte
)
