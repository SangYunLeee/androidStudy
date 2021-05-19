package com.example.myapplication.kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager

class RecyclerViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("what", "index: what")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        var carlist = createDumpContactList(10)
        var adaptor1 =  AdaptorForCar(carlist, LayoutInflater.from(this@RecyclerViewActivity))

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView_id)
        recyclerView.also {
            it.adapter = adaptor1
            it.layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
        }
    }

    fun createDumpContactList(index: Int) : ArrayList<Contact> {
        var contaxtList = ArrayList<Contact>()

        for (i in 1..index) {
            contaxtList.add(Contact("name" + i, "number" + i))
        }
        return contaxtList
    }
}

class AdaptorForCar(
    val itemList: ArrayList<Contact>,
    val inflator: LayoutInflater
): RecyclerView.Adapter<AdaptorForCar.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = inflator.inflate(R.layout.contract_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.carName.setText(itemList.get(position).name)
        holder.carNumber.setText(itemList.get(position).number)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var carName : TextView
        var carNumber : TextView

        init {
            carName =  itemView.findViewById(R.id.contact_name)
            carNumber =  itemView.findViewById(R.id.contact_number)
        }

    }


}
