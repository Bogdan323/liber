package com.example.liber.presentation.fragments.maincontent.searchgamesfragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.liber.R
import com.example.liber.databinding.FragmentSearchBinding
import com.example.liber.presentation.fragments.maincontent.searchgamesfragment.adapters.SearchGamesPagedListAdapter
import com.example.liber.presentation.viewmodels.SearchGamesViewModel
import com.example.liber.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment: Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val searchViewModel by viewModels<SearchGamesViewModel>()
    private var searchGamesListAdapter : SearchGamesPagedListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchGamesRecyclerView()
        focusEditTextAndTriggerKeyboard()

        searchGamesListAdapter?.onItemClick = {
            val action = SearchFragmentDirections.actionSearchFragmentToProductDetailsFragment(it)
            findNavController().navigate(action)
        }

        binding.edSearchGames.addTextChangedListener(afterTextChanged = {
            searchViewModel.getSearchedGames(it.toString())
            searchGamesListAdapter?.refresh()
        })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchViewModel.listOfSearchedGames.collect{
                searchGamesListAdapter?.submitData(it)
            }
        }
    }

    private fun focusEditTextAndTriggerKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.edSearchGames.requestFocus()
        imm.showSoftInput(binding.edSearchGames, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun setupSearchGamesRecyclerView() {
        searchGamesListAdapter = SearchGamesPagedListAdapter()
        binding.rvSearchGames.adapter = searchGamesListAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        searchGamesListAdapter = null
    }

}
