package school.sptech.zup.domain.model

data class Register(
    val id: Long,
    val nome: String,
    val email: String,
    val username: String,
    val senha: String,
    val influencer: Boolean,
    val cpf: String,
    val cnpj: String,
    val admin: Int, // Correção: alterado "Admin" para "admin"
    val foto: Byte // Correção: alterado "Byte" para "ByteArray"
)
