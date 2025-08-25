package com.example.template.ui.dashboard.settings

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.template.R
import com.example.template.core.data.Resource
import com.example.template.core.exception.NoConnectionException
import com.example.template.data.apiVm.apiVm
import com.example.template.data.sharedPrefs.PrefsHelper
import com.example.template.databinding.FragmentProfileBinding
import com.example.template.ui.login.SignInActivity
import com.example.template.utils.AppUtils
import com.example.template.utils.extensions.getError
import com.example.template.utils.extensions.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private val prefs by inject<PrefsHelper>()
    private var progressBar: Dialog? = null
    private val apiVm by viewModel<apiVm>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }


    private fun initViews() {
//        getUserProfile()
//      binding.tvUserName.text = prefs.name
        binding.llLogout.setOnClickListener {

            showLogoutDialog()

           /* val intent = Intent(requireActivity(),SignInActivity::class.java)
            prefs.sharedPref.edit().apply{
                clear()
                apply()
            }
            startActivity(intent)
            requireActivity().finish()
            */
        }

        binding.profileNameContainer.setOnClickListener {
//            findNavController().navigate(R.id.editProfileFragment)
        }
        binding.helpCenterContainer.setOnClickListener {
//            findNavController().navigate(R.id.helpCenterFragment)
        }

        binding.settingContainer.setOnClickListener {
//            findNavController().navigate(R.id.settingsFragment)
        }
      /*  binding.llEditProfile.setOnClickListener {
            findNavController().navigate(R.id.editProfileFragment)
        }*/
    }


    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                logOutUser()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }


    private fun getUserProfile() {
        Log.e("TAG", "getVenueDetailById: ${prefs.authToken}")
        progressBar = AppUtils.progressDialog(requireActivity())
        apiVm.userProfile(prefs.authToken)
            .observe(requireActivity()) { it ->
                println("PankajSingh:$it")
                when (it) {
                    is Resource.Success -> {
                        progressBar?.dismiss()

                        val data = it.data?.payload

//                      binding.userName.text = data?.first_name +" "+ data?.last_name
                        binding.userName.text = data?.email

                        val imageUrl = data?.profile_img

                        if (!imageUrl.isNullOrBlank()) {
                            Glide.with(requireContext())
                                .load(imageUrl)
                                .placeholder(R.drawable.profilefloter)
                                .error(R.drawable.profilefloter)
                                .into(binding.profileImage)
                        } else {
                            Glide.with(requireContext())
                                .load(R.drawable.profilefloter)
                                .into(binding.profileImage)
                        }

                        Toast.makeText(requireActivity(), "${it.data?.message}", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error ->{
                        progressBar?.dismiss()
                        when (it.exception) {
                            is NoConnectionException -> {
                                Toast.makeText(requireActivity(), "No internet", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                it.errorBody?.getError()?.errorMessage?.let { errorMessage ->
                                    requireActivity().toast(errorMessage)
                                }
                            }
                        }
                    }
                    else -> {

                    }
                }
            }
    }

    private fun logOutUser() {
        Log.e("TAG", "getVenueDetailById: ${prefs.authToken}")
        progressBar = AppUtils.progressDialog(requireActivity())
        apiVm.userLogout(prefs.authToken)
            .observe(requireActivity()) { it ->
                println("PankajSingh:$it")
                when (it) {
                    is Resource.Success -> {
                        progressBar?.dismiss()
                        logoutClearUser()
                        Toast.makeText(requireActivity(), "${it.data?.message}", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error ->{
                        progressBar?.dismiss()
                        when (it.exception) {
                            is NoConnectionException -> {
                                Toast.makeText(requireActivity(), "No internet", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                it.errorBody?.getError()?.errorMessage?.let { errorMessage ->
                                    requireActivity().toast(errorMessage)
                                }
                            }
                        }
                    }
                    else -> {

                    }
                }
            }
    }


    private fun customerSupport() {
        progressBar = AppUtils.progressDialog(requireActivity())

        apiVm.customerSupport()
            .observe(requireActivity())
            { it ->
                println("PankajSingh:$it")
                when (it) {
                    is Resource.Success -> {
                        progressBar?.dismiss()
                        val data = it.data?.payload

                        Log.d("TAG", "dkjdfkjgdlkd $data")

                        Toast.makeText(
                            requireActivity(),
                            "${it.data?.message}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    is Resource.Error ->{
                        progressBar?.dismiss()
                        when (it.exception) {
                            is NoConnectionException -> {
                                requireActivity().toast("No Internet")
                            }
                            else -> {
                                Log.e("TAG", "loginUser: ${it.errorBody?.getError()?.errorCode}", )
                                Log.e("TAG", "loginUser: ${it.errorBody?.getError()?.errorMessage}", )
                                Log.e("TAG", "loginUser: ${it.errorBody?.getError()?.statusCode}", )
                                it.errorBody?.getError()?.errorMessage?.let { errorMessage ->
                                    requireActivity().toast(errorMessage)
                                }
                            }
                        }
                    }
                    else -> {

                        progressBar?.dismiss()

                    }
                }
            }
    }


    private fun logoutClearUser() {
        prefs.sharedPref.edit().apply{
            clear()
            apply()
        }
        val intent = Intent(requireActivity(), SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


}