package com.example.fruitapp.presentation.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fruitapp.databinding.FragmentFruitListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FruitListFragment : Fragment() {

    private var _binding: FragmentFruitListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FruitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFruitListBinding.inflate(inflater, container, false)

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

        viewModel.onCreate()

        viewModel.fruitsModel.observe(viewLifecycleOwner) {
            fruits -> fruits.let { adapter.submitList(it) }
        }

        binding.fruitRecyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.floatingActionButton.setOnClickListener {
            val action =
                FruitListFragmentDirections.actionFruitListFragmentToAddFruitFragment()
            this.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}