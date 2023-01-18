package com.example.liber.presentation.fragments.maincontent.homefragment.categories.maincategoryfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.liber.R
import com.example.liber.databinding.FragmentMainCategoryBinding
import com.example.liber.presentation.fragments.maincontent.homefragment.HomeFragmentDirections
import com.example.liber.common.showBottomNavigationView
import com.example.liber.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainCategoryFragment: Fragment(R.layout.fragment_main_category) {

    private val binding by viewBinding(FragmentMainCategoryBinding::bind)
    private val viewModel by viewModels<MainCategoryViewModel>()
    //Adapters
    private var allGamesPagedListAdapter: AllGamesPagedListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.shimmerAllGames.startShimmer()
        setupAllGamesPagedListAdapter()
        collectAllGamesUiState()

        allGamesPagedListAdapter?.onItemClick = {
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(it)
            findNavController().navigate(action)
        }

    }

    private fun setupAllGamesPagedListAdapter() {
        allGamesPagedListAdapter = AllGamesPagedListAdapter()
        binding.rvAllGamesPaged.adapter = allGamesPagedListAdapter
    }

    private fun collectAllGamesUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.listOfAllGames.collect {
                allGamesPagedListAdapter?.submitData(it)
            }
        }
        binding.apply {
            shimmerAllGames.hideShimmer()
            shimmerAllGames.visibility = View.GONE
            rvAllGamesPaged.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigationView()
    }

    override fun onDestroy() {
        super.onDestroy()
        allGamesPagedListAdapter = null
    }
}