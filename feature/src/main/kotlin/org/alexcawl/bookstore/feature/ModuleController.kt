package org.alexcawl.bookstore.feature

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.bookstore.core.db.datasource.BookDataSource
import org.alexcawl.bookstore.core.db.entity.BookEntity
import org.alexcawl.bookstore.core.db.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ModuleController {
    @Autowired
    private lateinit var repository: BookDataSource

    @GetMapping("/module")
    fun checkModule(): Flow<String> = flow {
        for (i in 1..10) {
            emit(i.toString() + "\n")
            delay(500)
        }
    }

    @GetMapping("/books")
    fun getBooks(): Flow<BookEntity> = repository.findAll()
}