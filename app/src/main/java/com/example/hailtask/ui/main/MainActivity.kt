package com.example.hailtask.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hailtask.R
import com.example.hailtask.data.Api.Items.ItemsMyViewModelFactory
import com.example.hailtask.data.Repos.ItemDeatailsRepo
import com.example.hailtask.data.Repos.ItemsRepo
import com.example.hailtask.room.ItemDataBase
import com.example.hailtask.ui.itemDetails.ItemDetailsViewModel
import com.example.hailtask.ui.items.ItemsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var  navController: NavController

    val itemsViewModel: ItemsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment=supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController=navHostFragment.navController




    }
    }
