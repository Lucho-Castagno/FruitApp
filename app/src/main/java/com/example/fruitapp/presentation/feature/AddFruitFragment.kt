package com.example.fruitapp.presentation.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

    private lateinit var binding: FragmentAddFruitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_add_fruit, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.fruitName.text.toString(),
            binding.fruitFamily.text.toString(),
            binding.fruitGenus.text.toString(),
            binding.fruitOrder.text.toString(),
            binding.fruitCalories.text.toString(),
            binding.fruitCarbohydrates.text.toString(),
            binding.fruitFat.text.toString(),
            binding.fruitProtein.text.toString(),
            binding.fruitSugar.text.toString()
        )
    }

    private fun addNewFruit() {
        if (isEntryValid()) {
            viewModel.addNewFruit(
                binding.fruitName.text.toString(),
                binding.fruitFamily.text.toString(),
                binding.fruitGenus.text.toString(),
                binding.fruitOrder.text.toString(),
                binding.fruitCalories.text.toString(),
                binding.fruitCarbohydrates.text.toString(),
                binding.fruitFat.text.toString(),
                binding.fruitProtein.text.toString(),
                binding.fruitSugar.text.toString()
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