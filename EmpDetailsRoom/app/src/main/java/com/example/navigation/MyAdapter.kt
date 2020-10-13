package com.example.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.model.UsersInfo
import java.util.*


class MyAdapter : RecyclerView.Adapter<MyAdapter.UserHolder>() {
    private var userList:List<UsersInfo> = listOf()
    private lateinit var mcontext: Context
    private lateinit var view:View

    class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var uname: TextView =itemView.findViewById(R.id.name)
        var ulname: TextView=itemView.findViewById(R.id.lname)
        var uid: TextView=itemView.findViewById(R.id.uid)
        var uemail: TextView=itemView.findViewById(R.id.email)
        var gender: TextView=itemView.findViewById(R.id.gender)
        var status: TextView=itemView.findViewById(R.id.status)
       /* var create=itemView.findViewById<TextView>(R.id.createdAt)
        var update=itemView.findViewById<TextView>(R.id.updatedAt)*/
        val image: ImageView =itemView.findViewById(R.id.icon)
        var card: CardView =itemView.findViewById(R.id.user_card)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        mcontext=parent.context
        view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.uname.text= userList[position].fname
        holder.ulname.text= userList[position].lname
        holder.uid.text= userList[position].id.toString()
        holder.uemail.text= userList[position].email
        holder.gender.text= userList[position].gender
        holder.status.text= userList[position].status
       /* holder.create.text=userList.get(position).createdAt
        holder.update.text=userList.get(position).updatedAt*/
        if (userList[position].gender.toLowerCase(Locale.ROOT)=="male"){
            holder.image.setImageResource(R.drawable.male_icon)
        }
        else{
            holder.image.setImageResource(R.drawable.female_icon)
        }

       holder.card.setOnClickListener {

            val cfname:String=userList[position].fname
            val clname:String=userList[position].lname
            val cemail:String=userList[position].email
            val cgender:String=userList[position].gender
            val cstatus:String=userList[position].status
            val uid: Int =userList[position].id
            val action=MainFragmentDirections.Cardtodetail(cfname,clname,cemail,cgender,cstatus,uid)
            Navigation.findNavController(view).navigate(action)
        }
        /*try {
            //Invoking DELETE request
            holder.delete.setOnClickListener {
                // userList.(position)
                Log.d("Button","Button clicked")
                val service: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
                val call: Call<Unit> = service.deleteuser(userList[position].id)
                call.enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if(response.isSuccessful){
                            Toast.makeText(mcontext,"Status:${response.code()},User Deleted Successfully",Toast.LENGTH_SHORT).show()
                            Log.d("Delete","${response}")
                            val server: ApiInterface = RetroFitInstance().getRetrofitInstance().create(ApiInterface::class.java)
                            val getcall: Call<Users> = service.getUserData()
                            getcall.enqueue(object : Callback<Users> {
                                override fun onResponse(call: Call<Users>, response: Response<Users>) {
                                    Log.d("Repo", "${ response.code()}")
                                    if(response.isSuccessful)
                                    {
                                        setusers(response.body()!!.data)
                                    }
                                    else{
                                        Log.d("Application", "404 Not found")
                                    }

                                }
                                override fun onFailure(call: Call<Users>, t: Throwable) {
                                    Log.d("Application", "${t}")
                                }
                            })
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Log.d("Remove","Network")
                    }

                })
                notifyDataSetChanged()
            }
        }
       catch (e:NullPointerException){
           Log.d("Delete","${e}")
       }*/
    }
    fun setusers(userList: List<UsersInfo>){
        this.userList = userList
        notifyDataSetChanged()
    }

    /*override fun getViewModelStore(): ViewModelStore {
        return appviewModelStore
    }*/

}
