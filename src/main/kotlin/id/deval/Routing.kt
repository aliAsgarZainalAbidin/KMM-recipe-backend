package id.deval

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    logging()
    contentNegotiation()

    install(RoutingRoot){
        get("/"){
            call.respondText("Hello World")
        }
        get("/users/{username}"){
            val username = call.parameters["username"]
            val header = call.request.headers["Connection"]

            if(username == "admin"){
                call.response.header(name = "CustomHeader", "admin")
                call.respond(message = "Hello Admin", status = HttpStatusCode.OK)
            }
            call.respondText("Greetings, $username with header: $header")
        }
        get("/users"){
            val name = call.request.queryParameters["name"]
            val age = call.request.queryParameters["age"]
            call.respondText("Hello my name is $name, I'm $age years old")
        }
        get("/person"){
            try {
                val person = Person(name = "Ali", age = 200)
                call.respond(message = person, status = HttpStatusCode.OK)
            } catch (e : Exception){
                call.respond(message = "${e.message}", status = HttpStatusCode.BadRequest)
            }
        }
        staticResources(remotePath = "assets", "static")

        get("/welcome"){
            val name = call.request.queryParameters["name"]
            call.respondHtml {
                head {
                    title {
                        + "KMM recipe backend"
                    }
                }
                body {
                    if(name.isNullOrEmpty()){
                        h3 {
                            + "Welcome!"
                        }
                    } else {
                        h3 {
                            + "Welcome, $name!"
                        }
                    }
                    p {
                        + "Current Directory is : ${System.getProperty("user.dir")}"
                    }
                    img(src = "assets/image1.webp")
                }
            }
        }
    }
}

@Serializable
data class Person(
    val name : String,
    val age : Int
)