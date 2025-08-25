package com.example.template
import IntroSlideData
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.template.databinding.ActivityWalkThroughBinding
import com.example.template.ui.adapter.IntroAdapter
import com.example.template.ui.login.SignInActivity

class WalkThroughActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWalkThroughBinding
        private val introPages = listOf(
            IntroSlideData("Book Venue", "Showcase your turf with photos, rates, time slots, and more.", R.drawable.football_player_gif),
            IntroSlideData("Real Time Availability ", "You control when your venue is available — weekdays, weekends, hourly, or closed days.", R.drawable.balling_gif),
            IntroSlideData("Book Personal Trainer", "Boost your revenue with racket rentals, coaching, drinks & more.R", R.drawable.football_keepering_gig)
        )

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
           binding = DataBindingUtil.setContentView(this@WalkThroughActivity, R.layout.activity_walk_through)
            initViews()

        }

        private fun initViews() {
            val adapter = IntroAdapter(introPages, this)
            binding.viewPager.adapter = adapter
            // Setup TabLayout with ViewPager2
            binding.dotsIndicator.setViewPager2(binding.viewPager)
            binding.btnNext.setOnClickListener {
                if (binding.viewPager.currentItem < introPages.lastIndex) {
                    binding.viewPager.currentItem += 1
                } else {
                    launchMain()
                }
            }

            binding.btnSkip.setOnClickListener {
                launchMain()
            }
        }

        private fun launchMain() {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }



/*  private lateinit var binding: ActivityWalkThroughBinding

    private val introPages = listOf(
        IntroSlideData("Book Venue", "Showcase your turf with photos, rates, time slots, and more.", R.drawable.football_player_gif),
        IntroSlideData("Real Time Availability ", "You control when your venue is available — weekdays, weekends, hourly, or closed days.", R.drawable.balling_gif),
        IntroSlideData("Book Personal Trainer", "Boost your revenue with racket rentals, coaching, drinks & more.R", R.drawable.football_keepering_gig)
    )


    override fun getLayoutId(): Int {
        return R.layout.activity_walk_through
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT

//        binding = DataBindingUtil.setContentView(this@WalkThroughActivity, R.layout.activity_walk_through)
//        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        initViews()
    }

    private fun initViews() {
        val adapter = IntroAdapter(introPages,this)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.setViewPager2(binding.viewPager)
        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < introPages.lastIndex) {
                binding.viewPager.currentItem += 1
            } else {
                launchMain()
            }
        }

        binding.btnSkip.setOnClickListener {
            launchMain()
        }
    }

    private fun launchMain() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
}*/