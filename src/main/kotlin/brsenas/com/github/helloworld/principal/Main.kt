package brsenas.com.github.helloworld.principal

import brsenas.com.github.helloworld.model.Jogo
import brsenas.com.github.helloworld.services.ConsumoApi
import java.util.*


fun main() {
    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar: ")
    val busca = leitura.nextLine()

    val buscaApi = ConsumoApi()
    val informacaoJogo = buscaApi.buscaJogo(busca)

    var meuJogo: Jogo? = null

    val resultado = runCatching {
         meuJogo = Jogo(informacaoJogo.info.title,
            informacaoJogo.info.thumb)
    }
    resultado.onFailure {
        println("Jogo inexistente. Tente outro id")
    }
    resultado.onSuccess {
        println("Deseja inserir uma descricao personalizada? S/N")
        val opcao = leitura.nextLine()
        if(opcao.equals("s", true)){
            println("Insira a descrição personalizada para o jogo: ")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
        }else{
            meuJogo?.descricao = meuJogo?.titulo
        }

        resultado.onSuccess {
            println("Busca finalizada com sucesso")
        }

        println(meuJogo)
    }

}