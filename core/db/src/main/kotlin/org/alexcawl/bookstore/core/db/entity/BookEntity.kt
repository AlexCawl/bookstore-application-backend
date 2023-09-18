package org.alexcawl.bookstore.core.db.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("books")
data class BookEntity(
    @Id val id: UUID,
    @Column val name: String,
    @Column val description: String,
    @Column val price: Double,
    @Column val author: String
)