package com.example.hailtask.ui.itemDetails

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.hailtask.R
import com.example.hailtask.data.Api.ItemDetails.ItemDetailsViewModelFactory
import com.example.hailtask.data.Repos.ItemDeatailsRepo
import com.example.hailtask.data.model.GetItemDetails
import com.example.hailtask.databinding.FragmentItemDetailsBinding
import com.example.hailtask.util.Resource
import com.google.android.material.tabs.TabLayout

class ItemDetailsFragment : Fragment() {
    private lateinit var itemDetViewModel: ItemDetailsViewModel
    private lateinit var binding: FragmentItemDetailsBinding
    private val _args by navArgs<ItemDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        binding.fullDisc.setOnClickListener {
            if(binding.discDetails.maxLines==2){
                binding.discDetails.maxLines = Int.MAX_VALUE
                binding.fullDisc.text = "عرض الوصف المختصر "
            } else {
                binding.discDetails.maxLines = 2
                binding.fullDisc.text = "عرض الوصف كامل "
            }
        }
        return binding.root
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemDetViewModel = ViewModelProvider(this, ItemDetailsViewModelFactory(ItemDeatailsRepo()))[ItemDetailsViewModel::class.java]
        itemDetViewModel.getItemDet(_args.idArg)
        itemDetViewModel.itemDetLiveData.observe(viewLifecycleOwner, Observer { ress ->
            when (ress) {
                is Resource.Error -> ress.let { Toast.makeText(context,"error", Toast.LENGTH_LONG).show()
                    Log.e("error", "${ress.data}")}
                is Resource.Loading -> ress.let { Toast.makeText(context,"loading", Toast.LENGTH_LONG).show() }
                is Resource.Success -> ress.data?.data?.item_details?.let {itemDetails ->
                    binding.detdata = itemDetails
                    setImageViewPager(itemDetails.images)
                }

            }
        })

    }
    fun setImageViewPager(images: List<String>) {
        val imageSliderAdapter = pagerAdapter(requireContext(), images)
        binding.viewPager.adapter = imageSliderAdapter
        val tabLayout = binding.tabLayout
        tabLayout.setupWithViewPager(binding.viewPager, true)
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            val customText = "${i + 1}من${images.size}"
            tab?.text = customText
            tab?.view?.layoutParams?.width = 180
            tab?.view?.layoutParams?.height =80
            tab?.view?.findViewById<TextView>(android.R.id.text1)?.setTextColor(resources.getColor(R.color.viewpagercolor))
            tab?.view?.findViewById<TextView>(android.R.id.text1)?.textSize= 10F
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

}