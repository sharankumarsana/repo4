package com.example.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.navigation.model.ApiInterface
import com.example.navigation.model.Data
import com.example.navigation.model.RetroFitInstance
import com.example.navigation.model.UsersPost
import kotlinx.android.synthetic.main.fragment_postfrag.*
import kotlinx.android.synthetic.main.fragment_postfrag.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



class Postfrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_postfrag, container, false)
        view.cancelbtn.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_postfrag_to_mainFragment)}
        view.submitbtn.setOnClickListener {
            if (newname.text.toString()=="" || newemail.text.toString()=="" || newgender.text.toString()=="" || newstatus.text.toString()==""){
                Toast.makeText(context, "None of the Fields must be Empty", Toast.LENGTH_SHORT).show()
            }
            else{
                val newName=newname.text.toString()
                val newEmail=newemail.text.toString()
                val newGender=newgender.text.toString()
                val newStatus=newstatus.text.toString()
                val userdata= UsersPost(newName,newEmail,newGender,newStatus)
                Log.d("fab","$newName, $newEmail, $newGender, $newStatus")
                val postservice: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)

                val cal:Call<UsersPost> = postservice.postUserData("Bearer 520eeffab917d9d6ec292c016d2ec631c354a926977db9a1104c685275906180",userdata)
                cal.enqueue(object:Callback<UsersPost>{
                    override fun onResponse(call: Call<UsersPost>, response: Response<UsersPost>) {
                        Log.d("Fab","${response.code()}")
                        Log.d("Fab", "${response.body()}")
                    }

                    override fun onFailure(call: Call<UsersPost>, t: Throwable) {
                        Log.d("Fab","Ooops ${t.message}")
                    }

                })}}
        return view}}





