package org.alexcawl.bookstore.core.db.repository

import kotlinx.coroutines.flow.Flow
import org.alexcawl.bookstore.core.db.datasource.BookDataSource
import org.alexcawl.bookstore.core.db.entity.BookEntity
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class BookRepository(
    private val dataSource: BookDataSource
) {
    suspend fun getBookById(id: UUID): BookEntity? = dataSource.findById(id)

    fun getAllBooks(): Flow<BookEntity> = dataSource.findAll()
}