package org.alexcawl.bookstore.core.db.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.alexcawl.bookstore.core.db.datasource.BookDataSource
import org.alexcawl.bookstore.core.db.entity.BookEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class BookRepository(
    private val dataSource: BookDataSource
) {
    suspend fun getBookById(id: UUID): BookEntity? = dataSource.findById(id)

    fun getBooks(): Flow<BookEntity> = dataSource.findAll()

    suspend fun editBook(book: BookEntity): BookEntity = dataSource.save(book)

    fun editBooks(books: List<BookEntity>): Flow<BookEntity> = flow {
        val existedBooks = books.filter { dataSource.existsById(it.id) }
        dataSource.saveAll(existedBooks).collect {
            emit(it)
        }
    }

    suspend fun addBook(book: BookEntity) {
        dataSource.save(book)
    }

    suspend fun removeBook(id: UUID) {
        dataSource.deleteById(id)
    }
}