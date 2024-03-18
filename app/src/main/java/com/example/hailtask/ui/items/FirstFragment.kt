package com.example.hailtask.ui.items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.util.LocalePreferences.FirstDayOfWeek
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.databinding.FragmentFirstBinding
import com.example.hailtask.ui.itemDetails.ItemDetailsFragmentDirections
import com.example.hailtask.ui.main.MainActivity
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

        itemsViewModel.itemsLiveData.observe(viewLifecycleOwner, Observer { res ->
            when (res) {
                is Resource.Error -> res.let { Toast.makeText(context,"error", Toast.LENGTH_LONG).show()
                Log.e("error", "${res.data}")}
                is Resource.Loading -> res.let { Toast.makeText(context,"loading", Toast.LENGTH_LONG).show() }
                is Resource.Success -> res.data?.data?.items?.let {
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
        val id=data.id
        val action = firstFragmentDirections.actionFirstFragmentToItemDetailsFragment2(id)
        findNavController().navigate(action)
    }

}