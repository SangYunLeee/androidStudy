package com.example.myapplication.kotlin.outstagram

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OutstagramLoginActivity : AppCompatActivity() {

    lateinit var usernameView : EditText
    lateinit var userpasswordView : EditText
    lateinit var registerBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outstagram_login)

        initView()
        setupListener()
    }

    fun initView(){
        usernameView = findViewById(R.id.loginID)
        userpasswordView = findViewById(R.id.password)
        registerBtn = findViewById(R.id.signUpButton)
    }

    fun setupListener(): Unit {
            registerBtn.setOnClickListener {
                register()
                 Log.d("test", "registerBtn Clicked")
                
        }
    }

    fun register(): Unit {
        val username = usernameView.text.toString()
        val password = userpasswordView.text.toString()
        var registerID = RegisterID(username, password, password)
        (application as MasterApplication).service.registerOutStagram(registerID).enqueue(object :
            Callback<UserOG>{
            override fun onResponse(call: Call<UserOG>, response: Response<UserOG>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@OutstagramLoginActivity, "가입 성공", Toast.LENGTH_LONG).show()
                    val user = response.body()
                    val token = user?.token
                    saveUserToken(token)
                }
                else Toast.makeText(this@OutstagramLoginActivity, "가입 뭐냐", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<UserOG>, t: Throwable) {
                Toast.makeText(this@OutstagramLoginActivity, "가입 실패", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun saveUserToken(token: String?): Unit {
        var sp  = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.apply {
            putString("login_sp", token)
            commit()
        }

    }

    fun getUserName() : String {
        return usernameView.text.toString()
    }

    fun getUserPassword() : String {
        return userpasswordView.text.toString()
    }

}