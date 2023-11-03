package school.sptech.zup.data.mapper

import school.sptech.zup.data.model.RegisterResponse
import school.sptech.zup.domain.model.RegisterRequest

fun RegisterResponse.toDomain(): RegisterRequest{
    return RegisterRequest(
        id = id,
        nome = nome,
        email = email,
        username = username,
        senha = senha,
        influencer = influencer,
        cpf =  cpf,
        cnpj = cnpj,
        admin = Admin,
        foto = foto
    )
}