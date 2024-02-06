package com.example.fruitapp.presentation.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.fruitapp.R
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fruitapp.databinding.FragmentFruitListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FruitListFragment : Fragment() {

    private lateinit var binding: FragmentFruitListBinding

    private val viewModel: FruitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fruit_list, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FruitListAdapter {
            val action =
                FruitListFragmentDirections.actionFruitListFragmentToFruitDetailFragment(it.id)
            this.findNavController().navigate(action)
        }
        binding.fruitRecyclerView.adapter = adapter

        viewModel.onCreate(adapter)

        binding.fruitRecyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.floatingActionButton.setOnClickListener {
            val action =
                FruitListFragmentDirections.actionFruitListFragmentToAddFruitFragment()
            this.findNavController().navigate(action)
        }
    }

}