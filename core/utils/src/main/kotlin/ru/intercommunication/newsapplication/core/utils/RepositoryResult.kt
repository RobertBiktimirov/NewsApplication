package ru.intercommunication.newsapplication.core.utils

import retrofit2.HttpException

sealed class RepositoryResult<out T>

class RepositorySuccess<T : Any>(val data: T) : RepositoryResult<T>()
class RepositoryHttpError<T : Any>(val code: Int, val message: String?) : RepositoryResult<T>()
class RepositoryException<T : Any>(val e: Throwable) : RepositoryResult<T>()

inline fun <reified T : Any> handleError(block: (() -> T)): RepositoryResult<T> {
    return runCatching {
        block()
    }.fold(
        onSuccess = {
            RepositorySuccess(it)
        },
        onFailure = {
            if (it is HttpException) {
                RepositoryHttpError(it.code(), it.message)
            } else {
                RepositoryException(it)
            }
        }
    )
}