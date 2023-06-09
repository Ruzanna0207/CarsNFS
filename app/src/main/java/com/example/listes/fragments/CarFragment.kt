package com.example.listes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.listes.databinding.CarFragmentBinding

class CarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CarFragmentBinding.inflate(inflater)

        val markCar = binding.markOfCar
        val modelCar = binding.modelOfCar
        val photoCar = binding.photoOfCar
        val descriptionCar = binding.description

        //получение данных из предыдущего фрагмента при помощи bundle
        val brand = arguments?.getString("mark")
        val model = arguments?.getString("model")
        val imageUrl = arguments?.getString("imageUrl")
        val description = arguments?.getString("description")

        // Настройка TextView и ImageView
        markCar.text = brand
        modelCar.text = model
        descriptionCar.text = description

        Glide.with(requireContext())
            .load(imageUrl)
            .into(photoCar)

        return binding.root
    }
}