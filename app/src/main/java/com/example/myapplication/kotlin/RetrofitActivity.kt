package com.example.myapplication.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    lateinit var retrofit : Retrofit
    lateinit var service: RetrofitService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(RetrofitService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        requestGet()
        // requestPost2()

    }

    fun requestGet() {
        // GET 요청
        service.getStudentList().enqueue(object : Callback<ArrayList<Person>>{
            override fun onResponse(
                call: Call<ArrayList<Person>>,
                response: Response<ArrayList<Person>>
            ) {
                if(response.isSuccessful){
                    val personList = response.body()
                    Log.d("retrofit", "res: " + personList?.get(0)?.age ?: "null")
                }
            }

            override fun onFailure(call: Call<ArrayList<Person>>, t: Throwable) {

            }
        })
    }

    fun requestPost1() {
        // POST 요청
        val params = HashMap<String, Any>()
        params.apply {
            put("name", "ERROR 인간123")
            put("age", 404)
            put("intro", "Not Found 404")
        }
        service.createStudent(params).enqueue(object  : Callback<Person>{
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    val person = response.body()
                    Log.d("retrofit", "name: " + person?.name)
                    Log.d("retrofit", "id: " + person?.id)
                }
                else{
                    Log.d("retrofit", "error: " + response.message())
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Log.d("retrofit", "error: " + t.message)
            }
        })
    }

    fun requestPost2() {
        // POST 요청 (2)
        val params = Person(name = "post2", age = 1000, intro = "intro")
        service.createStudentEasy(params).enqueue(object  : Callback<Person>{
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    val person = response.body()
                    Log.d("retrofit", "name: " + person?.name)
                    Log.d("retrofit", "id: " + person?.id)
                }
                else{
                    Log.d("retrofit", "error: " + response.message())
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Log.d("retrofit", "error: " + t.message)
            }
        })
    }
}