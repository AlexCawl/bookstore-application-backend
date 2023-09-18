package org.alexcawl.bookstore.core.db.datasource

import kotlinx.coroutines.flow.Flow
import org.alexcawl.bookstore.core.db.entity.BookEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookDataSource : CoroutineCrudRepository<BookEntity, UUID> {
    fun findAllByAuthorContaining(author: String): Flow<BookEntity>

    fun findAllByNameContaining(name: String): Flow<BookEntity>
}