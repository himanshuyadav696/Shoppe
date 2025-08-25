package com.example.template.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.databinding.FragmentSetupNewPasswordBinding
import com.example.template.utils.AppUtils.navigateWithAnim

class SetupNewPasswordFragment : Fragment() {
    private lateinit var binding: FragmentSetupNewPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetupNewPasswordBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.btnSave.setOnClickListener {
            findNavController().navigateWithAnim(R.id.appIntroFragment)
        }
    }

}