package com.example.liber.presentation.fragments.maincontent.homefragment.categories.psgamesfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.liber.domain.usermodel.Category
import com.example.liber.common.Resource
import com.example.liber.presentation.fragments.maincontent.homefragment.categories.basecategoryfragment.BaseCategoryFragment
import com.example.liber.presentation.fragments.maincontent.homefragment.categories.basecategoryfragment.CategoryViewModel
import com.example.liber.presentation.fragments.maincontent.homefragment.categories.basecategoryfragment.BaseCategoryViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class PsGamesFragment : BaseCategoryFragment() {

    @Inject
    lateinit var firestore: FirebaseFirestore

    val viewModel by viewModels<CategoryViewModel> {
        BaseCategoryViewModelFactory(firestore, Category.PSGames)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.offerProducts.collectLatest {
                when(it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        offerAdapter.submitList(it.data)
                    }
                    is Resource.Error -> {
                        Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Unspecified -> {}
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.bestProducts.collectLatest {
                when(it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        bestProductsAdapter.submitList(it.data)
                    }
                    is Resource.Error -> {
                        Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Unspecified -> {}
                }
            }
        }
    }
}