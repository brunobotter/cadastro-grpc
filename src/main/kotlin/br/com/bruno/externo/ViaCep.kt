package br.com.bruno.externo

import br.com.bruno.cadastro.Cadastro
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client
interface ViaCep {


    @Get("https://viacep.com.br/ws/{cep}/json/" )
    fun consultaCep(@PathVariable(name = "cep") cep: String?): HttpResponse<CepResponse>?

}

data class CepResponse(val cep: String,
                       val logradouro: String,
                       val complemento: String,
                       val bairro: String,
                       val localidade: String,
                       val uf: String,
                       val ibge: String,
                       val ddd: String){
    fun toModel() = Cadastro(cep, logradouro, complemento, bairro, localidade, uf, ibge, ddd)

}