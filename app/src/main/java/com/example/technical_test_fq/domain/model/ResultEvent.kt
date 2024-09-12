package com.example.technical_test_fq.domain.model

sealed class ResultEvent <out T> {
    data class Success<out T>(val data: T) : ResultEvent<T>()
    data class ErrorCode(val code: Int): ResultEvent<Nothing>()
    data class Error(val exception: Exception) : ResultEvent<Nothing>()
    data object Loading : ResultEvent<Nothing>()
}