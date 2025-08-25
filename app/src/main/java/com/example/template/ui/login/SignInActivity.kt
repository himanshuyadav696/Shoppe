package com.example.template.ui.login
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.template.R
import com.example.template.databinding.ActivitySigninBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding

    private lateinit var navController: NavController

    private var purpose: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SignInActivity, R.layout.activity_signin)
        window.statusBarColor = ContextCompat.getColor(this,R.color.white)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        purpose = intent.getStringExtra("purpose")

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.sign_nav_graph)
        navGraph.setStartDestination(
            if (purpose == "register") R.id.registerFragment else R.id.loginFragment
        )

        // Apply updated graph
        navController.graph = navGraph

    }
}