package org.alexcawl.bookstore.book_feature.controller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.alexcawl.bookstore.book_feature.model.domain.BookModel
import org.alexcawl.bookstore.book_feature.model.dto.BookDto
import org.alexcawl.bookstore.book_feature.model.dto.ResponseWrapper
import org.alexcawl.bookstore.book_feature.model.dto.ResponseWrapper.*
import org.alexcawl.bookstore.book_feature.service.IBookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/book")
class BookController(
    @Autowired private val service: IBookService
) {

    @RequestMapping(
        value = ["/{id}"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    suspend fun getBookById(@PathVariable id: String): ResponseWrapper<BookDto> {
        try {
            val uuid: UUID = UUID.fromString(id)
            return service.getBook(uuid).let {
                when (it) {
                    null -> Failure("No book with id = $id")
                    else -> Success(
                        BookDto(
                            it.id,
                            it.name,
                            it.description,
                            it.price,
                            it.author
                        )
                    )
                }
            }
        } catch (exception: Exception) {
            return Failure(exception.message.toString())
        }
    }

    @RequestMapping(
        value = ["/all"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    fun getAllBooks(): Flow<ResponseWrapper<BookDto>> {
        return service.getBooks().map {
            Success(
                BookDto(
                    it.id,
                    it.name,
                    it.description,
                    it.price,
                    it.author
                )
            )
        }.catch<ResponseWrapper<BookDto>> {
            emit(Failure(it.message.toString()))
        }
    }

    @RequestMapping(
        value = ["/{id}"],
        method = [RequestMethod.PATCH],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    suspend fun editBookById(@PathVariable id: String, @RequestBody bookDto: BookDto): ResponseWrapper<BookDto> {
        try {
            val uuid: UUID = UUID.fromString(id)
            val book: BookModel = BookModel(
                bookDto.id,
                bookDto.name,
                bookDto.description,
                bookDto.price,
                bookDto.author
            )
            return when (val result = service.editBook(uuid, book)) {
                null -> Failure("No book with id = $id")
                else -> Success(
                    BookDto(
                        result.id,
                        result.name,
                        result.description,
                        result.price,
                        result.author
                    )
                )
            }
        } catch (exception: Exception) {
            return Failure(exception.message.toString())
        }
    }

    @RequestMapping(
        value = ["/all"],
        method = [RequestMethod.PATCH],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun editAllBooks(@RequestBody list: List<BookDto>): Flow<ResponseWrapper<BookDto>> {
        return service.editBooks(list.map {
            BookModel(
                it.id,
                it.name,
                it.description,
                it.price,
                it.author
            )
        }).map {
            Success(
                BookDto(
                    it.id,
                    it.name,
                    it.description,
                    it.price,
                    it.author
                )
            )
        }.catch<ResponseWrapper<BookDto>> {
            emit(Failure(it.message.toString()))
        }
    }

    @RequestMapping(
        value = ["/{id}"],
        method = [RequestMethod.POST],
        consumes = ["application/json"]
    )
    suspend fun addBook(@PathVariable id: String, @RequestBody bookDto: BookDto): ResponseWrapper<String> {
        return try {
            val book: BookModel = BookModel(
                bookDto.id,
                bookDto.name,
                bookDto.description,
                bookDto.price,
                bookDto.author
            )
            service.addBook(book)
            Success("Book with id = $id added!")
        } catch (exception: Exception) {
            Failure(exception.message.toString())
        }
    }

    @RequestMapping(
        value = ["/{id}"],
        method = [RequestMethod.DELETE]
    )
    suspend fun removeBook(@PathVariable id: String): ResponseWrapper<String> {
        return try {
            val uuid: UUID = UUID.fromString(id)
            service.removeBook(uuid)
            Success("Book with id = $id removed!")
        } catch (exception: Exception) {
            Failure(exception.message.toString())
        }
    }
}