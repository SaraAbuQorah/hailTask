package com.example.hailtask.ui.items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hailtask.data.model.Data
import com.example.hailtask.data.model.Item
import com.example.hailtask.databinding.FragmentFirstBinding
import com.example.hailtask.ui.main.MainActivity
import com.example.hailtask.util.Resource
import com.example.hailtask.util.setItemsAddress


class firstFragment : Fragment() ,ItemsAdapter.ItemsClickEvents {
    lateinit var itemsViewModel:ItemsViewModel
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemsViewModel = (activity as MainActivity).viewModel

        itemsViewModel.itemsLiveData.observe(viewLifecycleOwner, Observer { res ->
            when (res) {
                is Resource.Error -> res.let { Toast.makeText(context,"error", Toast.LENGTH_LONG).show()
                Log.e("error", "${res.data}")}
                is Resource.Loading -> res.let { Toast.makeText(context,"loading", Toast.LENGTH_LONG).show() }
                is Resource.Success -> res.data?.data?.items?.let {
                    Toast.makeText(context,"Success", Toast.LENGTH_LONG).show()
                    setItemsAdapter(it) }
            }
        })

    }
    fun setItemsAdapter(item: List<Item>) {
        val itemsAdapter = ItemsAdapter(this)
        binding.RecyclerViewItems.adapter = itemsAdapter
        itemsAdapter.submitList(item)
    }
    override fun selected(data: Item) {


    }


}