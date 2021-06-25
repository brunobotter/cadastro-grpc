package br.com.bruno.cadastro

import br.com.bruno.ConsultarRequest
import br.com.bruno.DeletarRequest
import br.com.bruno.SalvarRequest
import br.com.bruno.externo.ViaCep
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CadastroService(@Inject val cadastroRepository: CadastroRepository,
                      @Inject val viaCep: ViaCep) {

    fun salvar(request: SalvarRequest?): Cadastro {
        val cepResponse = viaCep.consultaCep(request?.cep).let {
            return cadastroRepository.save(it!!.body.get().toModel())
        }
    }

    fun deletar(request: DeletarRequest?) {
        cadastroRepository.deleteById(request?.id)
    }

    fun consultar(request: ConsultarRequest?): Cadastro {
        return cadastroRepository.findById(request?.id).get()
    }

    fun listarTodos(): MutableList<Cadastro> {
        return cadastroRepository.findAll()
    }

}
