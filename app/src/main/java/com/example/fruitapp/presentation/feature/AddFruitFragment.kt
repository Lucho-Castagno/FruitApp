package com.example.fruitapp.presentation.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fruitapp.R
import com.example.fruitapp.databinding.FragmentAddFruitBinding
import com.example.fruitapp.databinding.FragmentFruitListBinding
import com.example.fruitapp.domain.model.FruitItem

class AddFruitFragment : Fragment() {

    private val viewModel: FruitViewModel by viewModels()

    lateinit var fruit: FruitItem

    private var _binding: FragmentAddFruitBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_fruit, container, false)
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.fruitName.toString(),
            binding.fruitFamily.toString(),
            binding.fruitGenus.toString(),
            binding.fruitCalories.toString(),
            binding.fruitCarbohydrates.toString(),
            binding.fruitFat.toString(),
            binding.fruitProtein.toString(),
            binding.fruitSugar.toString()
        )
    }

    private fun addNewFruit() {
        if (isEntryValid()) {
            viewModel.addNewFruit(
                binding.fruitName.toString(),
                binding.fruitFamily.toString(),
                binding.fruitGenus.toString(),
                binding.fruitCalories.toString(),
                binding.fruitCarbohydrates.toString(),
                binding.fruitFat.toString(),
                binding.fruitProtein.toString(),
                binding.fruitSugar.toString()
            )
            val action = AddFruitFragmentDirections.actionAddFruitFragmentToFruitListFragment()
            this.findNavController().navigate(action)
        }
    }

}