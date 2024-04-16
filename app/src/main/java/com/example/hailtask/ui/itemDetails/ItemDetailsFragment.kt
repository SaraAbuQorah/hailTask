package com.example.hailtask.ui.itemDetails

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.hailtask.databinding.FragmentItemDetailsBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ItemDetailsFragment : Fragment() {

    lateinit var binding: FragmentItemDetailsBinding
    private val _args by navArgs<ItemDetailsFragmentArgs>()
    private val itemDetViewModel: ItemDetailsViewModel by viewModels()


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
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemDetViewModel.savedStateHandle.set("IdArg", _args.idArg)
        itemDetViewModel.itemDetails.observe(viewLifecycleOwner, Observer { resource ->
            if(resource!=null)
            {
                binding.detdata = resource
                setImageViewPager(resource.images!!)
            }
        })


    }



    fun setImageViewPager(images: List<String>) {
        val imageSliderAdapter = pagerAdapter(requireContext(), images)
        binding.viewPager.adapter = imageSliderAdapter

        binding.tabLayout.removeAllTabs()
        binding.tabLayout.addTab(binding.tabLayout.newTab(), true)


        updateTabText(binding.tabLayout, images.size)

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                updateTabText(binding.tabLayout, images.size, position)
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun updateTabText(tabLayout: TabLayout, totalTabs: Int, currentPosition: Int = 0) {
        if (tabLayout.tabCount == 1) {
            val tab = tabLayout.getTabAt(0)
            val customText = "${currentPosition + 1}من$totalTabs"
            tab?.text = customText
            tab?.view?.findViewById<TextView>(com.google.android.material.R.id.text)?.textSize=10f
        }
    }


}