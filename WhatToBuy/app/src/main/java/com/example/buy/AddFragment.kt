package com.example.buy

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.buy.database.BuylistViewModel
import com.example.buy.databinding.FragmentAddBinding
import com.example.buy.model.Model

class AddFragment : Fragment() {
    private lateinit var mBuylistViewModel: BuylistViewModel
    private var _binding:FragmentAddBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAddBinding.inflate(inflater,container,false)
        val view=binding.root
        mBuylistViewModel=ViewModelProvider(this).get(BuylistViewModel::class.java)
        binding.submit.setOnClickListener {
            val title=binding.title.text.toString()
            val groceries=binding.groceries.text.toString()
            val medicines=binding.mednote.text.toString()
            val others=binding.otheritemsnote.text.toString()
            if (TextUtils.isEmpty(title) || ((TextUtils.isEmpty(groceries)&&(TextUtils.isEmpty(medicines))&&(TextUtils.isEmpty(others))))){
                Toast.makeText(requireContext(),"None of the fields can be empty",Toast.LENGTH_SHORT).show()
            }
            else{
                val notes=Model(0,title,groceries,medicines, others)
                mBuylistViewModel.addlist(notes)
                Toast.makeText(requireContext(),"Note added",Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.add_main)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}