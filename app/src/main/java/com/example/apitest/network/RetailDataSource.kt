package com.example.apitest.network

import android.content.Context
import com.example.apitest.BuildConfig

import javax.inject.Inject
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/** to get the retrofit client*/
class RetailDataSource @Inject constructor() {

    companion object{
        private const val BASE_URL = "http://192.168.102.243:5001"
        //private  const val BASE_URL = "192.168.102.243:5005/UserInformation/GenerateOTP?applicationId=1&contactNo=01688656198"
    }

    /** function that create the retrofit client api*/
    fun <Api>buildApi(
        api: Class<Api>,
        context: Context
    ):Api{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    private fun getRetrofitClient(authenticator: Authenticator? = null): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .writeTimeout(100,TimeUnit.SECONDS)
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                }.build())
            }.also { client ->
                authenticator?.let { client.authenticator(it) }

                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }
}