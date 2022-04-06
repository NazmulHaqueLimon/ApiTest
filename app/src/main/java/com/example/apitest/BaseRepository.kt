package com.example.apitest

import com.example.apitest.network.BaseApi
import com.example.apitest.network.SafeApiCall

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {
    suspend fun logout() = safeApiCall {
        api.logout()
    }
}