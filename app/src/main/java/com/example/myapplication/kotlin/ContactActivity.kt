package com.example.myapplication.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityContactBinding
import com.example.myapplication.databinding.ContractListItemBinding

class ContactActivity : AppCompatActivity() {

    lateinit var binding: ActivityContactBinding
    lateinit var contract_binding: ContractListItemBinding
    val listContact = ArrayList<Contact>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var container = binding.contactContainer
        val inflactor =  LayoutInflater.from(this@ContactActivity)

        val contact1: Contact = Contact("이상윤", "010-4963-9575")
        val contact2: Contact = Contact("이학우", "010-2837-6840")
        val contact3: Contact = Contact("김정자", "010-2439-6840")
        listContact.add(contact1)
        listContact.add(contact2)
        listContact.add(contact3)

        for (i in 0 until listContact.size) {
            var itemView = inflactor.inflate(R.layout.contract_list_item, null)
            var name = itemView.findViewById<TextView>(R.id.contact_name)
            var number = itemView.findViewById<TextView>(R.id.contact_number)
            name.text = listContact.get(i).name
            number.text = listContact.get(i).number
            name.setOnClickListener{
                Log.d("hhhhh","hhhhhh")
                var intent12 = Intent(this@ContactActivity, ContactDetailActivity::class.java)
                intent12.putExtra("name" , listContact.get(i).name)
                intent12.putExtra("number" , listContact.get(i).number)
                startActivity(intent12)
            }
            container.addView(itemView)
        }
    }

    fun goToContactDetailActivity(){

    }
}

data class Contact (
    val name : String,
    val number : String
        )