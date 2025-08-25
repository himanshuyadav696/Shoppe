package com.example.template.ui.login
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.databinding.FragmentLoginBinding
import com.example.template.utils.AppUtils

class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }


    private fun initViews() {
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.typePasswordFragment)
        }
        AppUtils.setupHideKeyboardOnTouch(binding.root,requireActivity())

    }
}