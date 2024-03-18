package com.example.hailtask.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hailtask.R
import com.example.hailtask.data.Api.ItemDetails.ItemDetailsViewModelFactory
import com.example.hailtask.data.Api.Items.ItemsMyViewModelFactory
import com.example.hailtask.data.Repos.ItemDeatailsRepo
import com.example.hailtask.data.Repos.ItemsRepo
import com.example.hailtask.ui.itemDetails.ItemDetailsViewModel
import com.example.hailtask.ui.items.ItemsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var  navController: NavController
    lateinit var viewModel: ItemsViewModel
    lateinit var viewModel2: ItemDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment=supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController=navHostFragment.navController

        val itemRepo=ItemsRepo()
        val viewModelProviderFactory= ItemsMyViewModelFactory(itemRepo)
        viewModel= ViewModelProvider(this ,viewModelProviderFactory)[ItemsViewModel::class.java]

        val itemDetRepo=ItemDeatailsRepo()
        val DetviewModelProviderFactory= ItemDetailsViewModelFactory(itemDetRepo)
        viewModel2= ViewModelProvider(this ,DetviewModelProviderFactory)[ItemDetailsViewModel::class.java]

    }
    }
