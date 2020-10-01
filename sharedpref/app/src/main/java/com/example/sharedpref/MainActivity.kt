package com.example.sharedpref

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private var isremembered = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isremembered = sharedPreferences.getBoolean("CHECKBOX",false)
        if(isremembered)
        {


            intent = Intent(this,Secondactivity::class.java)
            startActivity(intent)
            finish()
        }

        done.setOnClickListener{
            val name = etname.text.toString()
            val age=etage.text.toString().toInt()
            val height=etheight.text.toString().toFloat()
            val weight=etweight.text.toString().toFloat()
            val phonenum=etphno.text.toString().toLong()
            val checked = checker.isChecked

            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("GOOD_NAME",name)
            editor.putInt("AGE",age)
            editor.putFloat("HEIGHT",height)
            editor.putFloat("WEIGHT",weight)
            editor.putLong("PHONE_NO",phonenum)
            editor.putBoolean("CHECKBOX",checked)
            editor.apply()

            if(checked) {
                Toast.makeText(this, getString(R.string.savedet), Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, getString(R.string.notsaved), Toast.LENGTH_LONG).show()
            }
            intent = Intent(this,Secondactivity::class.java)
            startActivity(intent)
            finish()
        }




    }
}