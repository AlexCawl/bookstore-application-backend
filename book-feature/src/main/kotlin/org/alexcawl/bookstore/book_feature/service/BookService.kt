package org.alexcawl.bookstore.book_feature.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.alexcawl.bookstore.book_feature.model.domain.BookModel
import org.alexcawl.bookstore.core.db.entity.BookEntity
import org.alexcawl.bookstore.core.db.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService(
    @Autowired private val repository: BookRepository,
) : IBookService {
    override suspend fun getBook(id: UUID): BookModel? {
        return withContext(Dispatchers.IO) {
            repository.getBookById(id).let {
                when (it) {
                    null -> null
                    else -> BookModel(
                        it.id,
                        it.name,
                        it.description,
                        it.price,
                        it.author
                    )
                }
            }
        }
    }

    override fun getBooks(): Flow<BookModel> {
        return repository.getBooks().map {
            BookModel(
                it.id,
                it.name,
                it.description,
                it.price,
                it.author
            )
        }
    }

    override suspend fun editBook(id: UUID, book: BookModel): BookModel? {
        return withContext(Dispatchers.IO) {
            when (repository.getBookById(id) != null) {
                false -> null
                true -> repository.editBook(
                    BookEntity(
                        book.id,
                        book.name,
                        book.description,
                        book.price,
                        book.author
                    )
                ).let {
                    BookModel(
                        it.id,
                        it.name,
                        it.description,
                        it.price,
                        it.author
                    )
                }
            }
        }
    }

    override fun editBooks(books: List<BookModel>): Flow<BookModel> {
        return repository.editBooks(
            books.map {
                BookEntity(
                    it.id,
                    it.name,
                    it.description,
                    it.price,
                    it.author
                )
            }
        ).map {
            BookModel(
                it.id,
                it.name,
                it.description,
                it.price,
                it.author
            )
        }
    }

    override suspend fun addBook(book: BookModel) {
        repository.addBook(
            BookEntity(
                book.id,
                book.name,
                book.description,
                book.price,
                book.author
            )
        )
    }

    override suspend fun removeBook(id: UUID) {
        repository.removeBook(id)
    }
}