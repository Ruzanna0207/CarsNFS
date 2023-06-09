package com.example.listes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.listes.AllCarsFragment
import com.example.listes.R
import com.example.listes.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater)

//Отображение изображения
        Glide.with(requireContext())
            .load("https://oboi-telefon.ru/wallpapers/112264/31395.jpg")
            .into(binding.mainImage)

        binding.buttonGoToList.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame, AllCarsFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

    companion object {
        fun newInstance3() = MainFragment()
    }
}