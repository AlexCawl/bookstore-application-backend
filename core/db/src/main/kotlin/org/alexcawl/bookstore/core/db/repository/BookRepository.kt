package org.alexcawl.bookstore.core.db.repository

import org.alexcawl.bookstore.core.db.datasource.BookDataSource
import org.springframework.stereotype.Component

@Component
class BookRepository(
    private val dataSource: BookDataSource
) {
    fun check(): String = "Hi!"
}