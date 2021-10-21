package com.movietest.common

sealed class ResponseResult<out T> {

    val isSuccess get() = this is Success
    val isFailure get() = !isSuccess

    data class Success<T>(val body: T) : ResponseResult<T>()

    data class Failure(val message: String, val error: Throwable) :
        ResponseResult<Nothing>()

}

inline fun <reified T> ResponseResult<T>.successBody(): T {
    return (this as ResponseResult.Success<T>).body
}