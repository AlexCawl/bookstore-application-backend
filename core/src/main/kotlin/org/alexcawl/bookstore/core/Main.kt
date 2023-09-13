package org.alexcawl.bookstore.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@RestController
@RequestMapping("/api")
class PingController {

    @Autowired
    private lateinit var getMe: String

    @GetMapping("/ping")
    fun ping(): String = "pong!"

    @GetMapping("/me")
    fun getMe(): String = getMe
}