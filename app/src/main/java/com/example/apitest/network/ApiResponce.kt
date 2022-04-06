package com.example.apitest.network
import okhttp3.ResponseBody

/**a generic type sealed class to handle the api success and failure responses */
sealed class ApiResponse<out T>{
    data class Success<out T>(val value: T): ApiResponse<T>()

    data class Failure(
        val isNetworkError:Boolean,
        val errorCode :Int?,
        val errorBody: ResponseBody?
    ): ApiResponse<Nothing>()

    object Loading : ApiResponse<Nothing>()
}
