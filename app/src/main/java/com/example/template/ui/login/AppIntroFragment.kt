package com.example.template.ui.login

import IntroSlideData
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.template.R
import com.example.template.databinding.FragmentAppIntroBinding
import com.example.template.ui.adapter.IntroAdapter

class AppIntroFragment : Fragment() {
    private lateinit var binding: FragmentAppIntroBinding
    private val introPages = listOf(
        IntroSlideData("Book Venue", "Showcase your turf with photos, rates, time slots, and more.", R.drawable.football_player_gif),
        IntroSlideData("Real Time Availability ", "You control when your venue is available — weekdays, weekends, hourly, or closed days.", R.drawable.balling_gif),
        IntroSlideData("Book Personal Trainer", "Boost your revenue with racket rentals, coaching, drinks & more.R", R.drawable.football_keepering_gig)
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
        val adapter = IntroAdapter(introPages, requireActivity())
        binding.viewPager.adapter = adapter
        // Setup TabLayout with ViewPager2
        binding.dotsIndicator.setViewPager2(binding.viewPager)
 /*       binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < introPages.lastIndex) {
                binding.viewPager.currentItem += 1
            } else {
                launchMain()
            }
        }

        binding.btnSkip.setOnClickListener {
            launchMain()
        }*/
    }
}