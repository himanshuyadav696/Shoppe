package com.example.template.ui.login

import IntroSlideData
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.template.MainActivity
import com.example.template.R
import com.example.template.databinding.FragmentAppIntroBinding
import com.example.template.ui.adapter.IntroAdapter

class AppIntroFragment : Fragment() {
    private lateinit var binding: FragmentAppIntroBinding
    private val introPages = listOf(
        IntroSlideData("Hello", "Showcase your turf with photos, rates, time slots, and more.", R.drawable.girl_shopping),
        IntroSlideData("Ready ", "You control when your venue is available — weekdays, weekends, hourly, or closed days.", R.drawable.girl_shopping),
        IntroSlideData("Lets explore", "Boost your revenue with racket rentals, coaching, drinks & more.R", R.drawable.girl_shopping)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppIntroBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }

    private fun initViews() {
        val adapter = IntroAdapter(
            introPages,
            requireActivity(),
            itemClick = {
                launchMain()
            }
        )
        binding.viewPager.adapter = adapter
        // Setup TabLayout with ViewPager2
        binding.dotsIndicator.setViewPager2(binding.viewPager)

    }
    private fun launchMain(){
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}