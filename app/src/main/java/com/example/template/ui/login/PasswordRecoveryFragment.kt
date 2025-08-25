package com.example.template.ui.login

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.databinding.FragmentPasswordRecoveryBinding

class PasswordRecoveryFragment : Fragment() {
    private lateinit var binding: FragmentPasswordRecoveryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentPasswordRecoveryBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }

    private fun initViews() {
        selectOption("sms")
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.enterPasswordRecoveryFragment)
        }
        binding.clSms.setOnClickListener {
            selectOption("sms")
        }
        binding.clEmail.setOnClickListener {
            selectOption("email")
        }
    }

    private fun selectOption(option: String) {
        when (option) {
            "sms" -> {
                // SMS selected
                binding.clSms.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.selected_light_blue))
               // binding.clSms.setBackgroundResource(R.drawable.selected_outline)
                binding.ivSmsCheck.setImageResource(R.drawable.check_filled)

                // Reset Email
                binding.clEmail.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.unselect_light_pink))
              //  binding.clEmail.setBackgroundResource(R.drawable.grey_outline)
                binding.ivEmail.setImageResource(R.drawable.check_empty)
            }

            "email" -> {
                // Email selected
                binding.clEmail.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.selected_light_blue))
               // binding.clEmail.setBackgroundResource(R.drawable.selected_outline)
                binding.ivEmail.setImageResource(R.drawable.check_filled)

                // Reset SMS
               // binding.clSms.setBackgroundResource(R.drawable.grey_outline)
                binding.clSms.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.unselect_light_pink))
                binding.ivSmsCheck.setImageResource(R.drawable.check_empty)
            }
        }
    }

}