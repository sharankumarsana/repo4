package com.example.buy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.buy.model.Model

class MyAdapter : RecyclerView.Adapter<MyAdapter.NoteHolder>() {

    private var notesList:List<Model> = listOf()
    private lateinit var view: View
    class NoteHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var titleText: TextView =itemView.findViewById(R.id.title)
        //var groceries: TextView = itemView.findViewById(R.id.groceries)!!
        var noteCard: CardView =itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.titleText.text= notesList[position].title
        //holder.groceries.text= notesList[position].groceries
        holder.noteCard.setOnClickListener {
            val title=notesList[position].title
            val groceries=notesList[position].groceries
            val medicines = notesList[position].medicines
            val others=notesList[position].others
            val id=notesList[position].id
            val navigate=MainFragmentDirections.mainNote(title,id,groceries,medicines,others)
            Navigation.findNavController(view).navigate(navigate)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setBuylist(buylist:List<Model>){
        this.notesList=buylist
        notifyDataSetChanged()
    }

}