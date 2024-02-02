package com.example.fruitapp.presentation.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitapp.databinding.FruitListItemBinding
import com.example.fruitapp.domain.model.FruitItem

class FruitListAdapter(private val onFruitClicked: (FruitItem) -> Unit) :
    ListAdapter<FruitItem, FruitListAdapter.FruitViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        return FruitViewHolder(
            FruitListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onFruitClicked(current)
        }
        holder.bind(current)
    }

    class FruitViewHolder(private var binding: FruitListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(fruit: FruitItem) {
                    binding.fruit = fruit
                }
            }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FruitItem>() {
            override fun areItemsTheSame(oldItem: FruitItem, newItem: FruitItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: FruitItem, newItem: FruitItem): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}