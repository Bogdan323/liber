package com.example.liber.presentation.fragments.maincontent.gamedetailfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.liber.R
import com.example.liber.presentation.fragments.maincontent.gamedetailfragment.adapters.PlatformsAdapter
import com.example.liber.presentation.fragments.maincontent.gamedetailfragment.adapters.ViewPager2Images
import com.example.liber.databinding.FragmentProductDetailsBinding
import com.example.liber.common.Resource
import com.example.liber.common.hideBottomNavigationView
import com.example.liber.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class GameDetailsFragment : Fragment(R.layout.fragment_product_details){
    private val args by navArgs<GameDetailsFragmentArgs>()
    private val binding by viewBinding(FragmentProductDetailsBinding::bind)
    private val viewModel by viewModels<DetailsViewModel>()
    private var viewPagerAdapter : ViewPager2Images? = null
    private var platformsAdapter : PlatformsAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideBottomNavigationView()

        val game = args.game

        setupViewpagerImages()
        setupPlatformsRv()

        binding.imageClose.setOnClickListener {
            findNavController().navigateUp()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.addToSavedGames.collectLatest {
                when(it) {
                    is Resource.Loading -> {
                        binding.buttonAddToMyList.startAnimation()
                    }
                    is Resource.Success -> {
                        binding.buttonAddToMyList.revertAnimation()
                    }
                    is Resource.Error -> {
                        binding.buttonAddToMyList.revertAnimation()
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    is Resource.Unspecified -> {}
                }
            }
        }

        binding.apply {
            tvGameName.text = game.name
            tvGameRating.text = game.rating.toString()
            tvGameDateRelease.text = getString(R.string.release_date, game.released)
            tvTotalRatings.text = getString(R.string.total_ratings, game.ratingsCount.toString())
            Glide.with(binding.root.rootView).load(game.backgroundImage).into(backgroundImage)
        }

        viewPagerAdapter!!.submitList(game.shortScreenshot)
        platformsAdapter!!.submitList(game.parentPlatform)
    }

    private fun setupViewpagerImages() {
        viewPagerAdapter = ViewPager2Images()
        binding.apply {
            viewpagerProductsImages.adapter = viewPagerAdapter
        }
    }

    private fun setupPlatformsRv() {
        platformsAdapter = PlatformsAdapter()
        binding.apply {
            rvPlatforms.adapter = platformsAdapter
            rvPlatforms.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        platformsAdapter = null
        viewPagerAdapter = null
    }
}