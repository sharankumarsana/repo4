package com.example.buy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.buy.model.Model

class MyAdapter : RecyclerView.Adapter<MyAdapter.CardHolder>() {

    private var buylist:List<Model> = listOf()
    private lateinit var view: View
    class CardHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var titleText: TextView =itemView.findViewById(R.id.title)
        //var groceries: TextView = itemView.findViewById(R.id.groceries)!!
        var noteCard: CardView =itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.titleText.text= buylist[position].title
        holder.noteCard.setOnClickListener {
            val title=buylist[position].title
            val groceries=buylist[position].groceries
            val medicines = buylist[position].medicines
            val others=buylist[position].others
            val id=buylist[position].id
            val navigate=MainFragmentDirections.mainNote(title,id,groceries,medicines,others)
            Navigation.findNavController(view).navigate(navigate)
        }
    }

    override fun getItemCount(): Int {
        return buylist.size
    }

    fun setBuylist(buylist:List<Model>){
        this.buylist=buylist
        notifyDataSetChanged()
    }

}