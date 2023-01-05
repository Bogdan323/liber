package com.example.liber.presentation.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.liber.R
import com.example.liber.presentation.activities.ContentActivity
import com.example.liber.databinding.FragmentIntroductionBinding
import com.example.liber.common.viewBinding
import com.example.liber.presentation.viewmodels.IntroductionViewModel.Companion.ACCOUNT_OPTIONS_FRAGMENT
import com.example.liber.presentation.viewmodels.IntroductionViewModel.Companion.SHOPPING_ACTIVITY
import com.example.liber.presentation.viewmodels.IntroductionViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IntroductionFragment : Fragment(R.layout.fragment_introduction) {
    private val binding by viewBinding(FragmentIntroductionBinding::bind)
    private val viewModel by viewModels<IntroductionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.navigateState.collect{
                when(it) {
                    SHOPPING_ACTIVITY -> {
                        Intent(requireActivity(), ContentActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    ACCOUNT_OPTIONS_FRAGMENT -> {
                        findNavController().navigate(it)
                    }
                    else -> Unit
                }
            }
        }

        binding.buttonStart.setOnClickListener {
            viewModel.startButtonClick()
            findNavController().navigate(R.id.action_introductionFragment_to_loginOptionsFragment)
        }
    }
}