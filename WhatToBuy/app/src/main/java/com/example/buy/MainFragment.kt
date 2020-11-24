package com.example.buy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buy.database.BuylistViewModel
import com.example.buy.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding:FragmentMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var buylistViewModel: BuylistViewModel
    lateinit var madapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentMainBinding.inflate(inflater,container,false)
        val view=binding.root

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        binding.contents.layoutManager=layoutManager
        madapter= MyAdapter()
        binding.contents.adapter=madapter

        buylistViewModel=ViewModelProvider(this).get(BuylistViewModel::class.java)
        buylistViewModel.readAll.observe(viewLifecycleOwner,{ buylist->madapter.setBuylist(buylist)
        })

        binding.add.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.main_add)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}