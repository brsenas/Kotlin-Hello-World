package brsenas.com.github.helloworld.principal

import brsenas.com.github.helloworld.model.Gamer
import brsenas.com.github.helloworld.model.Jogo
import brsenas.com.github.helloworld.services.ConsumoApi
import transformarEmIdade
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)

    val gamer = Gamer.criarGame(leitura)
    println(
        "Cadastro concluído com sucesso!\n" +
                "Dados do Gamer:"
    )
    println(gamer)
    println("Idade do gamer: " + gamer.dataNascimento?.transformarEmIdade())

    do {
        println("Digite um código de jogo para buscar: ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null

        val resultado = runCatching {
            meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb
            )
        }
        resultado.onFailure {
            println("Jogo inexistente. Tente outro id")
        }

        resultado.onSuccess {
            println("Deseja inserir uma descricao personalizada? S/N")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)) {
                println("Insira a descrição personalizada para o jogo: ")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
            } else {
                meuJogo?.descricao = meuJogo?.titulo
            }
            gamer.jogosBuscados.add(meuJogo)

        }
        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Jogos buscados:")
    println(gamer.jogosBuscados)

    println("Jogos ordenados por título")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Título "+it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }

    println("Jogos Filtrados\n" +
            jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()
    if (opcao.equals("s", true)){
        println(gamer.jogosBuscados)
        println("Informe a posicao do jogo que deseja excluir")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("Lista Atualizada:\n" +
            gamer.jogosBuscados)


    println("Busca finalizada com sucesso")

}