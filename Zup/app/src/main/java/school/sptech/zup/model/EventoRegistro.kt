package school.sptech.zup.model

data class EventoRegistro(
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
