package school.sptech.zup.data.model

import java.io.Serializable

data class CalculoPesoPorNoticiaIAResponse(
    val idNoticia : Int,
    val porcentagemPesoCompra: String?,
    val porcentagemPesoPensaEmCompra: String?,
    val porcentagemPesoNeutro: String?,
    val porcentagemPesoPenseEmVender: String?,
    val porcentagemPesoVenda: String?
) : Serializable
