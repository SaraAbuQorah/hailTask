    package com.example.hailtask.ui.items

    import android.os.Build
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.annotation.RequiresApi
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.viewModels
    import androidx.lifecycle.lifecycleScope
    import androidx.navigation.fragment.findNavController
    import androidx.paging.ExperimentalPagingApi
    import androidx.paging.PagingData
    import com.example.hailtask.data.model.itemss.Item
    import com.example.hailtask.databinding.FragmentFirstBinding
    import dagger.hilt.android.AndroidEntryPoint
    import kotlinx.coroutines.flow.collectLatest
    import kotlinx.coroutines.launch

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
        @ExperimentalPagingApi
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

                lifecycleScope.launch {
                    itemsViewModel.itemsFlow.collectLatest {
                        setItemsAdapter(it)
                    }
                }
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