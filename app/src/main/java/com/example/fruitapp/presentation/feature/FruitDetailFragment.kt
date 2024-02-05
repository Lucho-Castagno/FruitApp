package com.example.fruitapp.presentation.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.fruitapp.R
import com.example.fruitapp.databinding.FragmentFruitDetailBinding
import com.example.fruitapp.domain.model.FruitItem

class FruitDetailFragment : Fragment() {

    private val navigationArgs: FruitDetailFragmentArgs by navArgs()

    private var _binding: FragmentFruitDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FruitViewModel by viewModels()

    lateinit var fruit: FruitItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fruit_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.fruitId

        viewModel.retrieveFruit(id).observe(this.viewLifecycleOwner) {
            selectedFruit -> binding.fruit = selectedFruit!!
            this.fruit = selectedFruit
        }
    }

}