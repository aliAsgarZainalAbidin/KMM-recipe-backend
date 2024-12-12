package id.deval

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(){
    embeddedServer(Netty, port = applicationEnvironment().config.port, watchPaths = listOf("classes", "resources")){
        module()
    }.start(wait = true)
}

fun Application.module() {
    configureRouting()
}
