package org.alexcawl.bookstore.book_feature.controller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.alexcawl.bookstore.book_feature.model.dto.BookDto
import org.alexcawl.bookstore.book_feature.model.dto.ResponseWrapper
import org.alexcawl.bookstore.book_feature.service.IBookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
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
    suspend fun getBookById(@PathVariable id: String): ResponseWrapper<BookDto>? {
        try {
            val uuid: UUID = UUID.fromString(id)
            return service.getBook(uuid).let {
                when (it) {
                    null -> ResponseWrapper.Failure("No book with id = $id")
                    else -> ResponseWrapper.Success(
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
            return ResponseWrapper.Failure(exception.message.toString())
        }
    }

    @RequestMapping(
        value = ["/all"],
        method = [RequestMethod.GET],
        produces = ["application/json"]
    )
    fun getAllBooks(): Flow<ResponseWrapper<BookDto>> {
        try {
            return service.getBooks().map {
                ResponseWrapper.Success(
                    BookDto(
                        it.id,
                        it.name,
                        it.description,
                        it.price,
                        it.author
                    )
                )
            }
        } catch (exception: Exception) {
            return flow {
                emit(ResponseWrapper.Failure(exception.message.toString() + "idk"))
            }
        }
    }

    fun editBookById(): Nothing = TODO()

    fun editAllBooks(): Nothing = TODO()

    fun addBook(): Nothing = TODO()

    fun removeBook(): Nothing = TODO()
}