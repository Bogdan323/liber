package com.example.liber.presentation.fragments.maincontent.homefragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.liber.R
import com.example.liber.presentation.fragments.maincontent.homefragment.categories.adapters.HomeViewpagerAdapter
import com.example.liber.databinding.FragmentHomeBinding
import com.example.liber.presentation.fragments.maincontent.homefragment.categories.maincategoryfragment.MainCategoryFragment
import com.example.liber.presentation.fragments.maincontent.homefragment.categories.PsGamesFragment
import com.example.liber.common.viewBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeHeader.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        val categories = arrayListOf<Fragment>(
            MainCategoryFragment(),
            PsGamesFragment()
        )
        val viewpager2Adapter = HomeViewpagerAdapter(categories,
            childFragmentManager, lifecycle)
        binding.viewpagerHome.adapter = viewpager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome) { tab, position ->
            when(position) {
                0 -> tab.text = "Feed"
                1 -> tab.text = "Most popular"
            }
        }.attach()
    }
}