package org.alexcawl.bookstore.book_feature.model.dto

import kotlinx.serialization.Serializable
import org.alexcawl.core.configuration.UUIDSerializer
import java.util.*

@Serializable
data class BookDto(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    val name: String,
    val description: String,
    val price: Double,
    val author: String
)