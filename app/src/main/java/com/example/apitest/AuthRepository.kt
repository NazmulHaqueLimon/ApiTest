package com.example.apitest

import com.example.apitest.network.AuthApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: AuthApi,

    ): BaseRepository(api){

     fun generateOtp(code: Int, phoneNumber: String) = safeApiCall {
        api.generateOtp(code,phoneNumber)
    }

}