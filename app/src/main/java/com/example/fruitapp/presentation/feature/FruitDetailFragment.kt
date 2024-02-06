package com.example.fruitapp.presentation.feature

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.fruitapp.R
import com.example.fruitapp.databinding.FragmentFruitDetailBinding
import com.example.fruitapp.domain.model.FruitItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FruitDetailFragment : Fragment() {

    private val navigationArgs: FruitDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentFruitDetailBinding

    private val viewModel: FruitViewModel by viewModels()

    lateinit var fruit: FruitItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_fruit_detail, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val id = navigationArgs.fruitId
        Log.i("FRUIT__ID", id.toString())
        viewModel.retrieveFruit(id)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}