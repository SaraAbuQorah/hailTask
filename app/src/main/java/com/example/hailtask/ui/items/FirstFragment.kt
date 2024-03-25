package com.example.hailtask.ui.items

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.hailtask.util.NetworkUtils
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemsViewModel = (activity as MainActivity).viewModel

        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        val isConnected = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

        if (isConnected) {
            itemsViewModel.refreshItems()
        } else {
            Toast.makeText(
                requireContext(),
                "No internet connection. Please check your network settings.",
                Toast.LENGTH_SHORT
            ).show()
        }



        itemsViewModel.itemsLiveData.observe(viewLifecycleOwner, Observer { resource ->
            if(resource!=null) {
                    resource?.let { items ->
                        setItemsAdapter(items)
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