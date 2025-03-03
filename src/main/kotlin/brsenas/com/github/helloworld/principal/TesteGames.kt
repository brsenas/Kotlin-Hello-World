import brsenas.com.github.helloworld.model.Gamer

fun main(){

    val gamer1 = Gamer("Bruno", "brunosena@gmail.com")
    println(gamer1)

    val gamer2 = Gamer("Pedro", "pedrin@gmail.com", "20/03/2005",
        "pedrin")
    println(gamer2)

    //Scope Functions
    gamer1.let {
        it.dataNascimento = "15/02/2000"
        it.usuario = "brunosena"
    }.also { println(gamer2.idInterno) }
    println(gamer1)

}


