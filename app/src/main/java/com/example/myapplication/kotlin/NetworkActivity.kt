package com.example.myapplication.kotlin

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

interface AsyncResponse {
    fun processFinish(output: ArrayList<Person>?)
}

//네트워크를 통해 데이터를 얻은 후 어답터에 넣어줌으로써 업데이트
class NetworkActivity   : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        var task = DownloadFilesTask(object : AsyncResponse {
            override fun processFinish(output: ArrayList<Person>?) {
                output?.let {

                    for (item in output)
                        Log.d("conn", "result.age: " + item.age)
                    var adaptor1 =  AdaptorForPerson(output, LayoutInflater.from(this@NetworkActivity))

                    val recyclerView : RecyclerView = findViewById(R.id.recyclerView_id)
                    recyclerView.also {
                        it.adapter = adaptor1
                        it.layoutManager = LinearLayoutManager(this@NetworkActivity)
                    }
                }
            }
        })
        task.execute("d")

    }

    private class DownloadFilesTask(delegate: AsyncResponse?) : AsyncTask<String?, Int?, ArrayList<Person>>(){
        var delegate: AsyncResponse? = null
        init {
            this.delegate = delegate
        }

        override fun doInBackground(vararg params: String?): ArrayList<Person> {
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
                Log.d("conn", "buffer :" + buffer)
            }

            val data = Gson().fromJson(buffer, Array<Person>::class.java)
            for (item in data)
                Log.d("conn", "item.age: " + item.age)
            return data.toCollection(ArrayList())
        }

        override fun onPostExecute(result: ArrayList<Person>?) {
            super.onPostExecute(result)
            delegate?.processFinish(result)
        }
    }
}

class AdaptorForPerson(
    var itemList: ArrayList<Person>,
    val inflator: LayoutInflater
) : RecyclerView.Adapter<AdaptorForPerson.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var carName : TextView
        var carNumber : TextView

        init {
            carName =  itemView.findViewById(R.id.person_name)
            carNumber =  itemView.findViewById(R.id.person_info)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = inflator.inflate(R.layout.person_list_item, parent, false)
        return AdaptorForPerson.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.carName.setText(itemList.get(position).name)
        holder.carNumber.setText(itemList.get(position).intro)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}