package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.databinding.ActivityBrowserBinding
import com.example.myapplication.databinding.CalculatorMainBinding
import java.net.URI

class BrowserActivity : AppCompatActivity() {
    lateinit var binding: ActivityBrowserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var text = binding.browsingEditText
        text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        var button = binding.browsingButton
        button.setOnClickListener {
//            var url =  binding.browsingEditText.text.toString()
//            val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//            startActivity(i)

            val url = "www.example.com"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

    }

}