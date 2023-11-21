package school.sptech.zup.data.mapper

import school.sptech.zup.domain.model.RegisterRequest

fun school.sptech.zup.data.model.RegisterResponse.toDomain(): RegisterRequest {
    return RegisterRequest(
        nome = nome,
        email = email,
        username = username,
        senha = senha,
        influencer = influencer,
        autenticado = autenticado,
        foto = foto,
        cpf = cpf,
        cnpj = cnpj,
    )
}