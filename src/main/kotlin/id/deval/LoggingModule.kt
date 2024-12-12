package id.deval

import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*

fun Application.logging(){
    install(CallLogging)

}