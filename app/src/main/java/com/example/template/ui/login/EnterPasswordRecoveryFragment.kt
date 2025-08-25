package com.example.template.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.databinding.FragmentEnterPasswordRecoveryBinding

class EnterPasswordRecoveryFragment : Fragment() {
    private lateinit var binding: FragmentEnterPasswordRecoveryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnterPasswordRecoveryBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }

    private fun initViews() {
        setupOtpInputs()
    }

    private fun setupOtpInputs() {
        val editTexts = listOf(binding.pin1, binding.pin2, binding.pin3, binding.pin4)

        for (i in editTexts.indices) {
            editTexts[i].addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        editTexts[i].background = ContextCompat.getDrawable(requireActivity(), R.drawable.circle_filled_bg)
                        editTexts[i].setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))

                        if (i < editTexts.size - 1) {
                            editTexts[i + 1].requestFocus()
                        } else {
                            // All filled → Collect OTP
                            val otp = editTexts.joinToString("") { it.text.toString() }
                            findNavController().navigate(R.id.setupNewPasswordFragment)
                            //Toast.makeText(requireActivity(), "OTP: $otp", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        editTexts[i].background = ContextCompat.getDrawable(requireActivity(), R.drawable.circle_bg)
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            // Handle backspace (move to previous)
            editTexts[i].setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (editTexts[i].text.isEmpty() && i > 0) {
                        editTexts[i - 1].requestFocus()
                        editTexts[i - 1].text = null
                    }
                }
                false
            }
        }
        editTexts[0].requestFocus()
    }


}