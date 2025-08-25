package com.example.template.ui.login
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.template.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        initObservers()
        return binding.root
    }


    private fun initObservers() {
        binding.btnDone.setOnClickListener {

        }
        binding.tvCancel.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }


}