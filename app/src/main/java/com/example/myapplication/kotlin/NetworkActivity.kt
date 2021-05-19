package com.example.myapplication.kotlin

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)
        var task = DownloadFilesTask()
        task.execute("test")

    }

    private class DownloadFilesTask : AsyncTask<String?, Int?, Long>(){
        override fun doInBackground(vararg params: String?): Long {
            val urlString = "http://mellowcode.org/json/students/"

            val url = URL(urlString)
            val connection : HttpURLConnection = url.openConnection() as HttpURLConnection

            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json")

            var buffer = ""
            if(connection.responseCode == HttpURLConnection.HTTP_OK){
                Log.d("conn", "inputStream :" + connection.inputStream)
                val reader = BufferedReader(
                        InputStreamReader(
                                connection.inputStream,
                                "UTF-8"
                        )
                )
                buffer = reader.readLine()
            }
            return 0
        }
    }
}