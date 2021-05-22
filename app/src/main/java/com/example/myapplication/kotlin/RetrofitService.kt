package com.example.myapplication.kotlin

import com.example.myapplication.kotlin.outstagram.RegisterID
import com.example.myapplication.kotlin.outstagram.UserOG
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("json/students/")
    fun getStudentList() : Call<ArrayList<Person>>

    @POST("json/students/")
    fun createStudent(
        @Body params : HashMap<String, Any>
        ) : Call<Person>

    @POST("json/students/")
    fun createStudentEasy(
        @Body person : Person
    ) : Call<Person>

    @POST("user/signup/")
    fun registerOutStagram(
        @Body registerId : RegisterID
    ) : Call<UserOG>


}