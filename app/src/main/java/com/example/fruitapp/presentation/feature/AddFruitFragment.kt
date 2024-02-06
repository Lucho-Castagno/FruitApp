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
import com.example.fruitapp.databinding.FragmentFruitDetailBinding
import com.example.fruitapp.databinding.FragmentFruitListBinding
import com.example.fruitapp.domain.model.FruitItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        _binding =  FragmentAddFruitBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.fruitName.toString(),
            binding.fruitFamily.toString(),
            binding.fruitGenus.toString(),
            binding.fruitOrder.toString(),
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
                binding.fruitOrder.toString(),
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveAction.setOnClickListener {
            addNewFruit()
        }
    }

}