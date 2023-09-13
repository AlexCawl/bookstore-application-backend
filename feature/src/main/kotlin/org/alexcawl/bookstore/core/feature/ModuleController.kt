package org.alexcawl.bookstore.core.feature

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ModuleController {
    @GetMapping("/module")
    fun checkModule(): String = "I'm in module!"
}