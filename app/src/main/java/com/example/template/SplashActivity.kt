package com.example.template

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.template.data.sharedPrefs.PrefsHelper
import com.example.template.databinding.ActivitySplashBinding
import com.example.template.ui.login.SignInActivity
import com.example.template.utils.AppConstants
import com.example.template.utils.AppUtils
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity(){
        private lateinit var binding: ActivitySplashBinding
        private val prefs by inject<PrefsHelper>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            binding = ActivitySplashBinding.inflate(layoutInflater)
            setContentView(binding.root)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            binding.btnGetStarted.setOnClickListener {
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("purpose","register")
                startActivity(intent)
               // overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            }
            binding.tvLogin.setOnClickListener {
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("purpose","login")
                startActivity(intent)
            }

           // checkForLogin()
        }


        private fun checkForLogin() {
            val isLoggedIn =
                AppUtils.getPrefBoolean(AppConstants.USER_PREF.IS_LOGIN, this)
            Handler(mainLooper).postDelayed({
                if (prefs.isLoggedIn) {
                    intent = Intent(this, MainActivity::class.java)
                } else {
                    intent = Intent(this, WalkThroughActivity::class.java)
                }
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }, 3000)
        }

    }
