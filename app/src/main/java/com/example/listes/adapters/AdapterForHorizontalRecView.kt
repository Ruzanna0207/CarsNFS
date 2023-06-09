package com.example.listes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listes.databinding.ForHorizontalRecViewBinding

//адаптер имеет слушатель нажатий в виде лямбда-выражения
class AdapterForHorizontalRecView(private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<AdapterForHorizontalRecView.ViewHolder>() {

    var items: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ForHorizontalRecViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(items[position])

        viewHolder.itemView.setOnClickListener { onItemClick(items[position]) }
    }

    override fun getItemCount() = items.size

    class ViewHolder(private val binding: ForHorizontalRecViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(car: String) {
            binding.nameOfMark.text = car
        }
    }
}