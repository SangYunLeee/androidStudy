package com.example.myapplication.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.myapplication.R

class ListViewActivity : AppCompatActivity() {

    var list = ArrayList<Contact>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        for (i in 0 until 30)
        {
            list.add(Contact("" + i + " 번째 이름" ,"" + i + " 번째 전화번호" ))
        }
        var listView = findViewById<ListView>(R.id.listView)
        var adapter = ListViewAdapter(list, layoutInflater)
        listView.adapter = adapter
    }



}

class ListViewAdapter(
        val carForList: ArrayList<Contact>,
        val layoutInflater: LayoutInflater
) : BaseAdapter() {
    override fun getCount(): Int {
        return carForList.size
    }

    override fun getItem(position: Int): Any {
        return carForList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return carForList.size.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view =  layoutInflater.inflate(R.layout.contract_list_item, null)
        var name = view.findViewById<TextView>(R.id.contact_name)
        var number = view.findViewById<TextView>(R.id.contact_number)
        name.text = carForList.get(position).name
        number.text = carForList.get(position).number
        return view
    }

}