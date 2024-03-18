package com.example.hailtask.ui.screenImage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.hailtask.R
import com.example.hailtask.databinding.FragmentImageBinding

class ImageFragment : Fragment() {
    private lateinit var binding: FragmentImageBinding
    private val imageArgs: ImageFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        imageArgs.imageArg?.let { imageUrl ->
            Glide.with(this)
                .load(imageUrl.toUri())
                .error(R.drawable.haiil)
                .into(binding.imageView)
        }
        return binding.root
    }

}