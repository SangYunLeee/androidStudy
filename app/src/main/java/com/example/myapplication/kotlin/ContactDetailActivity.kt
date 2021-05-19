package com.example.myapplication.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityContactDetailBinding
import com.example.myapplication.databinding.CalculatorMainBinding

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name =  intent?.getStringExtra("name")
        var number =  intent?.getStringExtra("number")
        binding.contactName.text = name
        binding.contactNumber.text = number




    }
}