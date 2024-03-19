package com.example.figmatojsongenerator.data.remote.interceptors

import com.example.figmatojsongenerator.data.local.PrefrenceManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthHeaderInterceptor(val preferencesManager: PrefrenceManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = runBlocking {
            preferencesManager.getToken().firstOrNull()
        }
        if (token != null) {
            request.addHeader("X-FIGMA-TOKEN", token)
        }


        return chain.proceed(request.build())
    }

}