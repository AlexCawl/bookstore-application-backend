package org.alexcawl.bookstore.book_feature.service

import kotlinx.coroutines.flow.Flow
import org.alexcawl.bookstore.book_feature.model.domain.BookModel
import java.util.UUID

interface IBookService {
    suspend fun getBook(id: UUID): BookModel?

    fun getBooks(): Flow<BookModel>
}