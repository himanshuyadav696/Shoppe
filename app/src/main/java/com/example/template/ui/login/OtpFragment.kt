package com.example.template.ui.login
import RegistrationModel
import SendOtpModel
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.template.MainActivity
import com.example.template.R
import com.example.template.core.data.Resource
import com.example.template.core.exception.NoConnectionException
import com.example.template.data.apiVm.apiVm
import com.example.template.data.sharedPrefs.PrefsHelper
import com.example.template.databinding.FragmentOtpBinding
import com.example.template.utils.AppUtils
import com.example.template.utils.extensions.getError
import com.example.template.utils.extensions.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtpFragment : Fragment() {
    private lateinit var binding: FragmentOtpBinding
    private var fromScreen = ""
    private var phoneNumber = ""
    private val apiVm by viewModel<apiVm>()
    private val prefs by inject<PrefsHelper>()
    private var progresbar: Dialog? = null
    private var countdown: CountDownTimer?=null
    private var isTimerRunning = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fromScreen = it.getString("fromScreen").toString()
            phoneNumber = it.getString("phoneNumber").toString()
            Log.e("TAG", "onCreate: $fromScreen $phoneNumber", )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtpBinding.inflate(inflater)
        binding.ivBack.setOnClickListener {
            it.findNavController().popBackStack()
        }
        AppUtils.setupHideKeyboardOnTouch(binding.root,requireActivity())
        initViews()
        return binding.root
    }

    private fun initViews() {
        Log.e("TAG", "initViews: $fromScreen", )
        startTimer()

        val otp = arguments?.getString("otp") ?: ""
        if (otp.isNotEmpty()) {
            binding.pinview.setValue(otp)
        }

        binding.pinview.setPinViewEventListener { pinView, fromUser ->
            val otp = pinView.value
            if (otp.length == 4) {
                hideKeyboard(binding.pinview)
            }
        }

        binding.btnVerify.setOnClickListener {
            val intent = Intent(requireActivity(),MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
//            validation()
        }
    }

    private fun validation() {
        val otp = binding.pinview.value

        if (!isTimerRunning) {
            requireActivity().toast("OTP expired. Please request a new OTP.")
            return
        }

        if(otp.isEmpty()){
            requireActivity().toast("Please enter OTP")
        }else{
            val model = RegistrationModel(
                phone = phoneNumber,
                otp = otp,
                device_token = "device-token",
                device_type = "android",
//              user_type = ""
            )
            verifyOtp(model)
        }
    }

    fun startTimer() {

        if (countdown != null) {
            super.onDestroy()
            isTimerRunning = true
            countdown?.cancel()
        }
        countdown = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                var t1 = ""
                var t2: String
                val min = seconds / 60
                val sec = seconds % 60
                t2 = if (sec < 10) {
                    "0${sec}"
                } else {
                    "$sec"
                }
                if (min < 10) {
                    t1 = "0${min}"
                } else {
                    t2 = "$min"
                }
                binding.tvTimer.text = "$t1:$t2"
                binding.tvResend.isEnabled = false
                context?.let {
                    binding.tvResend.setTextColor(ContextCompat.getColor(it, R.color.grey))
                }
            }
            @SuppressLint("StringFormatInvalid")
            override fun onFinish() {
                isTimerRunning = false
                binding.tvTimer.text = "00:00"
                binding.tvResend.isEnabled = true
                binding.pinview.clearValue()

                context?.let {
                    binding.tvResend.setTextColor(ContextCompat.getColor(it, R.color.blue))
                }
                binding.tvResend.setOnClickListener {

                    val model = SendOtpModel(
                        phone = phoneNumber
                    )
                    reSendOtp(model)
                }
            }
        }.start()
    }

    private fun reSendOtp(model: SendOtpModel?) {
        progresbar = AppUtils.progressDialog(requireActivity())

        apiVm.reSendOtp(model)
            .observe(requireActivity()
            ) { it ->
                println("PankajSingh:$it")
                when (it) {
                    is Resource.Success -> {
                        progresbar?.dismiss()
                        Toast.makeText(requireActivity(), "${it.data?.message}", Toast.LENGTH_SHORT).show()
                        startTimer()
                    }
                    is Resource.Error ->{
                        progresbar?.dismiss()
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
                    else ->
                        {

                    }
                }
            }
    }


    private fun verifyOtp(model: RegistrationModel?) {
        progresbar = AppUtils.progressDialog(requireActivity())
        apiVm.verifyOtp(model)
            .observe(requireActivity()
            ) { it ->
                println("PankajSingh:$it")
                when (it) {
                    is Resource.Success -> {
                        val data = it.data?.payload
                        prefs.name = data?.name.toString()
                        prefs.authToken = data?.auth_token.toString()
                        prefs.deviceType = data?.device_type.toString()
                        prefs.email = data?.email.toString()
                        prefs.phone = data?.phone.toString()
                        prefs.isLoggedIn = true
                        prefs.profileImage = data?.profile_img.toString()
                        Toast.makeText(requireActivity(), "${it.data?.message}", Toast.LENGTH_SHORT).show()
                        if(it.data?.code==1){
                            countdown?.cancel()
                            val intent = Intent(requireActivity(),MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        }
                        progresbar?.dismiss()

                    }
                    is Resource.Error ->{
                        progresbar?.dismiss()
                        when (it.exception) {
                            is NoConnectionException -> {

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
                    else ->
                        {

                    }
                }
            }
    }

    private fun hideKeyboard(view: View) {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}