package com.example.listes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listes.adapters.AdapterForHorizontalRecView
import com.example.listes.data.Car
import com.example.listes.databinding.HorizontalItemLayoutBinding
import com.example.listes.databinding.VerticalItemLayoutBinding

class CarsAdapter(
    private val items: List<Car>,
    private val listener: Clickable,
    private val callback: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewTypeFirst = 0  //для опред-ия позиции
    private val viewTypeSecond = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            viewTypeFirst -> {
                val binding =
                    HorizontalItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                FirstCellViewHolder(binding)
            }
            viewTypeSecond -> {
                val binding =
                    VerticalItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                OtherCellViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }
//--------------------------------------------------------------------------------------------------
    //выбор viewHolder в зависимости от позиции
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is FirstCellViewHolder -> {
                holder.bind()
            }
            is OtherCellViewHolder -> {
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) viewTypeFirst else viewTypeSecond
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class FirstCellViewHolder(private val binding: HorizontalItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val list = items.subList(1, 21).map { it.mark }.toSet() //список для горизонтального rec.view

        private val innerRecyclerView: RecyclerView = binding.allCars

        fun bind() {
            val customAdapter = AdapterForHorizontalRecView(callback) //создание адаптера для вложенного rec.view
            customAdapter.items = list.toList()

            val recyclerView = innerRecyclerView
            recyclerView.adapter = customAdapter
        }
    }

    inner class OtherCellViewHolder(private val binding: VerticalItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val markOfCar: TextView = binding.mark
        private val modelOfCar: TextView = binding.model
        private val photoOfCar: ImageView = binding.photo

        fun bind(item: Car) {
            markOfCar.text = item.mark
            modelOfCar.text = item.model

            Glide.with(itemView.context)
                .load(item.image)
                .centerCrop()
                .into(photoOfCar)

            itemView.setOnClickListener {//передаваемые параметры для слушателя нажатий в вертикальном rec.view, при помощи интерфейса
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onClick(item, position)
                }
            }
        }
    }
}
//интерфейс для обработки нажатий в вертикальном rec.view
interface Clickable {
    fun onClick(car: Car, position: Int)
}