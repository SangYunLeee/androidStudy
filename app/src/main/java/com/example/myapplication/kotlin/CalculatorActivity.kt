package com.example.myapplication.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.databinding.CalculatorMainBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: CalculatorMainBinding
    var resultValue: String = "0"
    var prograssValue: String = "0"
    var flag_plus : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CalculatorMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
        binding.text0.setOnClickListener{
            if(prograssValue != "0") {
                prograssValue(0)
            }
        }
        binding.text1.setOnClickListener{
            prograssValue(1)
        }
        binding.text2.setOnClickListener{
            prograssValue(2)
        }
        binding.text3.setOnClickListener{
            prograssValue(3)
        }
        binding.text4.setOnClickListener{
            prograssValue(4)
        }
        binding.text5.setOnClickListener{
            prograssValue(5)
        }
        binding.text6.setOnClickListener{
            prograssValue(6)
        }
        binding.text7.setOnClickListener{
            prograssValue(7)
        }
        binding.text8.setOnClickListener{
            prograssValue(8)
        }
        binding.text9.setOnClickListener{
            prograssValue(9)
        }

        binding.textPlus.setOnClickListener{
            flag_plus = true
            resultValue = prograssValue
            prograssValue = "0"
            binding.resultText.text = prograssValue
        }

        binding.textCa.setOnClickListener{
            flag_plus = false
            prograssValue = "0"
            resultValue = "0"
            binding.resultText.text = resultValue
        }

        binding.textEqual.setOnClickListener{
            if(flag_plus == true) {
                flag_plus = false
                var _resultValue: Int = 0
                _resultValue = resultValue.toInt() + prograssValue.toInt()
                resultValue = _resultValue.toString()
                prograssValue = "0"
                binding.resultText.text = resultValue
            }
        }
    }

    fun prograssValue(value : Int){
        if(prograssValue == "0")
            prograssValue = ""
        prograssValue  =  prograssValue + value.toString()
        binding.resultText.text = prograssValue
    }
}