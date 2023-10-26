package school.sptech.zup.data.mapper

import school.sptech.zup.data.model.RegisterResponse
import school.sptech.zup.domain.model.Register

fun RegisterResponse.toDomain(): Register{
    return Register(
        id = id,
        nome = nome,
        email = email,
        username = username,
        senha = senha,
        influencer = influencer,
        cpf =  cpf,
        cnpj = cnpj,
        Admin = Admin,
        foto = foto
    )
}