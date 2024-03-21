package com.example.hailtask.ui.items

import com.example.hailtask.util.NetworkUtils
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.databinding.FragmentFirstBinding
import com.example.hailtask.ui.main.MainActivity
import com.example.hailtask.util.NetworkUtils.isNetworkAvailable
import com.example.hailtask.util.Resource


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

        itemsViewModel.itemsLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Error -> {
                    if(isNetworkAvailable(requireContext())){
                    Toast.makeText(requireContext(), "Error: ${resource.message}", Toast.LENGTH_SHORT).show()
                    Log.e("Error", resource.message ?: "Unknown error")
                }}
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    resource.data?.let { items ->
                        setItemsAdapter(items)
                    }
                }
            }
        })
    }
    fun setItemsAdapter(item: List<Item>) {
        val itemsAdapter = ItemsAdapter(this)
        binding.RecyclerViewItems.adapter = itemsAdapter
        itemsAdapter.submitList(item)
    }
    override fun selected(data: Item) {
        val id=data.id
        val action = firstFragmentDirections.actionFirstFragmentToItemDetailsFragment2(id)
        findNavController().navigate(action)
    }

}