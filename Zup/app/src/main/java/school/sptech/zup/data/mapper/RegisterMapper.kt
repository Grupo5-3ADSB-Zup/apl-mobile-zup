package school.sptech.zup.data.mapper

import school.sptech.zup.data.model.RegisterResponse
import school.sptech.zup.domain.model.RegisterRequest

fun RegisterResponse.toDomain(): RegisterRequest{
    return RegisterRequest(
        nome = nome,
        username = username,
        senha = senha,
        cpf =  cpf,
        cnpj = cnpj,


    )
}