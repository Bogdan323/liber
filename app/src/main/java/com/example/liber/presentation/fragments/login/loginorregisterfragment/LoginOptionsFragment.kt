package com.example.liber.presentation.fragments.login.loginorregisterfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.liber.R
import com.example.liber.databinding.FragmentLoginOptionsBinding
import com.example.liber.common.viewBinding

class LoginOptionsFragment : Fragment(R.layout.fragment_login_options) {
    private val binding by viewBinding(FragmentLoginOptionsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonRegisterNewAc.setOnClickListener {
                findNavController().navigate(R.id.action_loginOptionsFragment_to_registerFragment)
            }
            buttonLoginAc.setOnClickListener {
                findNavController().navigate(R.id.action_loginOptionsFragment_to_loginFragment)
            }
        }

    }
}