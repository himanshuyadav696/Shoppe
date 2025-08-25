package com.example.template.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.databinding.FragmentTypePasswordBinding
import com.example.template.utils.AppUtils

class TypePasswordFragment : Fragment() {
    private lateinit var binding: FragmentTypePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTypePasswordBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.tvForgot.setOnClickListener {
            findNavController().navigate(R.id.passwordRecoveryFragment)
        }
        AppUtils.setupHideKeyboardOnTouch(binding.root,requireActivity())
    }
}