package salvar

import br.com.bruno.*
import br.com.bruno.cadastro.CadastroService
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
class SalvarEndpoint(val cadastroService: CadastroService) : CadastroGrpcServiceGrpc.CadastroGrpcServiceImplBase(){

    override fun salvar(request: SalvarRequest?, responseObserver: StreamObserver<SalvarResponse>?) {
        val response = cadastroService.salvar(request)
        responseObserver?.onNext(SalvarResponse.newBuilder()
            .setBairro(response.bairro)
            .setCep(response.cep)
            .setComplemento(response.complemento)
            .setDdd(response.ddd)
            .setIbge(response.ibge)
            .setLocalidade(response.localidade)
            .setLogradouro(response.logradouro)
            .setUf(response.uf)
            .build())
        responseObserver?.onCompleted()
    }

    override fun deletar(request: DeletarRequest?, responseObserver: StreamObserver<DeletarResponse>?) {
        cadastroService.deletar(request)
        responseObserver?.onNext(DeletarResponse.newBuilder()
            .setMessage("DELETADO")
            .build())
        responseObserver?.onCompleted()
    }

    override fun consultar(request: ConsultarRequest?, responseObserver: StreamObserver<ConsultarResponse>?) {
        val response = cadastroService.consultar(request)
        responseObserver?.onNext(
            ConsultarResponse.newBuilder()
            .setBairro(response.bairro)
            .setCep(response.cep)
            .setComplemento(response.complemento)
            .setDdd(response.ddd)
            .setIbge(response.ibge)
            .setLocalidade(response.localidade)
            .setLogradouro(response.logradouro)
            .setUf(response.uf)
            .build())
        responseObserver?.onCompleted()
    }

    override fun listar(request: ListaRequest?, responseObserver: StreamObserver<ListaResponse>?) {
        val response = cadastroService.listarTodos().map {
            ListaResponse.Lista.newBuilder()
                .setId(it.id.toString())
                .setCep(it.cep)
                .setBairro(it.bairro)
                .setComplemento(it.complemento)
                .setDdd(it.ddd)
                .setIbge(it.ibge)
                .setLocalidade(it.localidade)
                .setLogradouro(it.logradouro)
                .setUf(it.uf)
                .build()
        }
        responseObserver?.onNext(
            ListaResponse.newBuilder()
                .addAllLista(response)
                .build())
        responseObserver?.onCompleted()
    }
}