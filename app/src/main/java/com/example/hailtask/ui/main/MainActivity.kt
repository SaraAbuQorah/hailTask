package com.example.hailtask.ui.main

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hailtask.R
import com.example.hailtask.data.Api.ItemsInterface
import com.example.hailtask.data.Api.ItemsMyViewModelFactory
import com.example.hailtask.data.Repos.ItemsRepo
import com.example.hailtask.ui.items.ItemsAdapter
import com.example.hailtask.ui.items.ItemsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var  navController: NavController
    lateinit var viewModel: ItemsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment=supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController=navHostFragment.navController

        val itemRepo=ItemsRepo()
        val viewModelProviderFactory=ItemsMyViewModelFactory(itemRepo)
        viewModel= ViewModelProvider(this ,viewModelProviderFactory)[ItemsViewModel::class.java]

    }
    }
