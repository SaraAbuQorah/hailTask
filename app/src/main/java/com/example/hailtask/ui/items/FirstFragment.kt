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
    import androidx.lifecycle.lifecycleScope
    import androidx.navigation.fragment.findNavController
    import androidx.paging.PagingData
    import com.example.hailtask.data.model.itemss.Item
    import com.example.hailtask.databinding.FragmentFirstBinding
    import com.example.hailtask.ui.itemDetails.ItemDetailsViewModel
    import com.example.hailtask.ui.main.MainActivity
    import com.example.hailtask.util.NetworkUtils.isNetworkAvailable
    import com.example.hailtask.util.Resource
    import dagger.hilt.android.AndroidEntryPoint
    import kotlinx.coroutines.flow.collectLatest
    import kotlinx.coroutines.launch
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


            val connectivityManager =
                requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            val isConnected = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

            if (isConnected) {

            lifecycleScope.launch {
                itemsViewModel.itemsdata.collectLatest { pagingData ->
                    setItemsAdapter(pagingData)
                }
            }
            } else {
                Toast.makeText(
                    requireContext(),
                    "No internet connection. Please check your network settings.",
                    Toast.LENGTH_SHORT
                ).show()

            itemsViewModel.itemsFromDb.observe(viewLifecycleOwner) { pagingData ->
                viewLifecycleOwner.lifecycleScope.launch {
                    setItemsAdapter(pagingData)
                }
            }}

        }
        private suspend fun setItemsAdapter(item: PagingData<Item>) {
            val itemsAdapter = ItemsAdapter(this)
            binding.RecyclerViewItems.adapter = itemsAdapter
            itemsAdapter.submitData(item)
        }
        override fun selected(data: Item) {
            val id=data.id
            val action = firstFragmentDirections.actionFirstFragmentToItemDetailsFragment2(id)
            findNavController().navigate(action)

        }

    }