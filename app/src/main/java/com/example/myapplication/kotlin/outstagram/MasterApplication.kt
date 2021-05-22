package com.example.myapplication.kotlin.outstagram

import android.app.Application
import android.app.Service
import android.content.Context
import com.example.myapplication.kotlin.RetrofitService
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MasterApplication : Application() {

    lateinit var service : RetrofitService
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        createRetrofit()

    }

    fun createRetrofit(){
        val header = Interceptor{
            val original = it.request()

            if (checkIsLogin()) {
                var request = original.newBuilder()
                    .header("Authorization", "token " + getUserToken()?: "")
                    .build()
                it.proceed(request)
            } else {
                it.proceed(original)
            }
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(header)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        service = retrofit.create(RetrofitService::class.java)
    }

    fun checkIsLogin() : Boolean {
        return (getUserToken() != null)
    }

    fun getUserToken() : String? {
        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val token = sp.getString("login_sp", "null")
        if (token == "null" || token != null) return null
        else return token
    }
}