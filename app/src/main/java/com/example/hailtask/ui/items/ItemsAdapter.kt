package com.example.hailtask.ui.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hailtask.data.model.itemss.Item
import com.example.hailtask.databinding.ItemCardBinding
import com.example.hailtask.databinding.ItemCardBinding.inflate

class ItemsAdapter(private val clickListener: ItemsClickEvents) :
    PagingDataAdapter<Item, ItemsAdapter.ViewHolder>(ItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item!!)
    }


    class ViewHolder(private val binding:ItemCardBinding ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ItemsClickEvents, item: Item) {
            binding.data = item
            binding.clickEvents = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    interface ItemsClickEvents {
        fun selected(data: Item)
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

}