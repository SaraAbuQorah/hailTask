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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.databinding.FragmentFirstBinding
import com.example.hailtask.ui.itemDetails.ItemDetailsViewModel
import com.example.hailtask.ui.main.MainActivity
import com.example.hailtask.util.NetworkUtils.isNetworkAvailable
import com.example.hailtask.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class firstFragment : Fragment() ,ItemsAdapter.ItemsClickEvents {

     private val itemsViewModel: ItemsViewModel by viewModels()
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsViewModel.itemsLiveData.observe(viewLifecycleOwner, Observer { resource ->
            resource?.let { items ->
                setItemsAdapter(items)
            }
        })
    }
    private fun setItemsAdapter(item: List<Item>) {
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