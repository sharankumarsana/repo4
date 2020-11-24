package com.example.buy

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.buy.database.BuylistViewModel
import com.example.buy.databinding.FragmentNoteBinding
import com.example.buy.model.Model


class EditFragment : Fragment() {
    lateinit var mBuylistViewModel: BuylistViewModel
    private val args: EditFragmentArgs by navArgs()
    private var _binding: FragmentNoteBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNoteBinding.inflate(inflater,container,false)
        val view=binding.root
        mBuylistViewModel=ViewModelProvider(this).get(BuylistViewModel::class.java)

        val title=args.title
       val groceries = args.groceries
        val medicines=args.medicines
        val others=args.others

        binding.titleText.text=title
      binding.groceriesedittext.setText(groceries,TextView.BufferType.EDITABLE)
        binding.medicineseditnote.setText(medicines,TextView.BufferType.EDITABLE)
        binding.otheritemseditnote.setText(others,TextView.BufferType.EDITABLE)

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(item.itemId==R.id.delete){
            val deletelist=Model(args.id,args.title,args.groceries,args.medicines,args.others)
            val builder= AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){
                    _,_->mBuylistViewModel.deletelist(deletelist)
                Toast.makeText(requireContext(), "${args.title} list Removed ", Toast.LENGTH_LONG).show()
                view?.let { Navigation.findNavController(it).navigate(R.id.note_main) }
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${args.title}?")
            builder.setMessage("Are you sure you want to delete the list ${args.title}?")
            builder.create().show()
        }
        else if(item.itemId==R.id.update){
            val updatedgroceries=binding.groceriesedittext.text.toString()
           val updatedmedicines = binding.medicineseditnote.text.toString()
           val updatedothers = binding.otheritemseditnote.text.toString()
            val update=Model(args.id,args.title,updatedgroceries,updatedmedicines,updatedothers)
            mBuylistViewModel.viewlist(update)
           Toast.makeText(requireContext(), "${args.title} Updated ", Toast.LENGTH_LONG).show()
           view?.let { Navigation.findNavController(it).navigate(R.id.note_main) }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}