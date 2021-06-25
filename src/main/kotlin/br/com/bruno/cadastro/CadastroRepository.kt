package br.com.bruno.cadastro

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CadastroRepository: JpaRepository<Cadastro, Long> {

}
