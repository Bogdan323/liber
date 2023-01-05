package com.example.liber.presentation.fragments.maincontent.homefragment.categories

import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.liber.R
import com.example.liber.presentation.fragments.maincontent.homefragment.categories.adapters.BestProductsAdapter
import com.example.liber.databinding.FragmentBaseCategoryBinding
import com.example.liber.common.viewBinding

open class BaseCategoryFragment : Fragment(R.layout.fragment_base_category) {
    private val binding by viewBinding(FragmentBaseCategoryBinding::bind)
    protected val offerAdapter : BestProductsAdapter by lazy { BestProductsAdapter() }
    protected val bestProductsAdapter : BestProductsAdapter by lazy { BestProductsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOfferRecyclerView()
        setupBestProductsRecyclerView()
        binding.rvOffer.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollHorizontally(1) && dx != 0){
                    onOfferPagingRequest()
                }
            }
        })

        binding.nestedScrollViewBaseCategory
            .setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (v.getChildAt(0).bottom <= v.height + scrollY) {
                    onBestProductsPagingRequest()
                }
            })
    }

    open fun onOfferPagingRequest(){

    }

    open fun onBestProductsPagingRequest() {

    }

    private fun setupBestProductsRecyclerView() {
        binding.rvOffer.adapter = offerAdapter
        binding.rvOffer.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupOfferRecyclerView() {
        binding.rvBestProducts.adapter = bestProductsAdapter
        binding.rvBestProducts.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }
}