package org.alexcawl.bookstore.book_feature.model.dto

import kotlinx.serialization.Serializable
@Serializable
sealed class ResponseWrapper <out T>{
    @Serializable
    data class Failure<out T>(val cause: String) : ResponseWrapper<T>()
    @Serializable
    data class Success<out T>(val payload: T) :  ResponseWrapper<T>()
}