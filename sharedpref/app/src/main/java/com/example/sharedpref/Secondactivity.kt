package com.example.sharedpref
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_secondactivity.*
class Secondactivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondactivity)

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val bmitext:TextView=findViewById(R.id.bmitext)
        val name = preferences.getString("GOOD_NAME","")
        saname.text=getString(R.string.NameinSA,name)
        val age=preferences.getInt("AGE",0)
        sage.text = getString(R.string.AgeinSA,age.toString())
        val height=preferences.getFloat("HEIGHT",0.0f)
        sheight.text = getString(R.string.HeightinSA,height.toString())
        val weight=preferences.getFloat("WEIGHT",0.0f)
        saweight.text = getString(R.string.WeightinSA,weight.toString())
        val phno=preferences.getLong("PHONE_NO",0)
        saphno.text = getString(R.string.phinSA,phno.toString())
        val yourbmi = (weight)/((height)*(height))
        calculatebmi.setOnClickListener {
            if (yourbmi < 18) {
                bmitext.text = getString(R.string.eatmore, yourbmi.toString(), name)
            }
            else if((yourbmi<20)&&(yourbmi>18)){
                bmitext.text=getString(R.string.healthy, yourbmi.toString(),name)
                    }
            else
            {
                bmitext.text=getString(R.string.burnmore,yourbmi.toString(),name)
            }
        }
        logout.setOnClickListener{
            val editor=preferences.edit()
            editor.clear()
            editor.apply()
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}