package org.alexcawl.bookstore.book_feature.model.domain

import java.util.*

data class BookModel(
    val id: UUID,
    val name: String,
    val description: String,
    val price: Double,
    val author: String
)