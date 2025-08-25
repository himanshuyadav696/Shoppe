package com.example.template.ui.dashboard.settings

import Payload
import UpdateProfile
import android.Manifest
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.example.template.R
import com.example.template.core.data.Resource
import com.example.template.core.exception.NoConnectionException
import com.example.template.data.apiVm.apiVm
import com.example.template.data.sharedPrefs.PrefsHelper
import com.example.template.databinding.FragmentEditProfileBinding
import com.example.template.utils.AppUtils
import com.example.template.utils.extensions.getError
import com.example.template.utils.extensions.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding
    private val prefs by inject<PrefsHelper>()
    private var progressBar: Dialog? = null
    private val apiVm by viewModel<apiVm>()
    var selectedGender =""
    var selectedDate = ""

    private lateinit var imageView: ImageView
    private lateinit var imageUri: Uri



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()


        imageView = binding.profileImage

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.frImage.setOnClickListener {
            showImagePickerBottomSheet()
        }

        binding.clSelectDate.setOnClickListener {
            showDatePicker()
        }

        val genderOptions = listOf("Male", "Female", "Other")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, genderOptions)
        binding.mySpinner.adapter = adapter
        binding.mySpinner.setSelection(0) // set default

        binding.mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedGender = genderOptions[position]
                Log.d("TAG", "SelectedGender: $selectedGender")

                // If you want to store it somewhere globally
                // this.selectedGender = selectedGender
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

    }

    private fun initViews() {
        getUserDetail()
        AppUtils.setupHideKeyboardOnTouch(binding.root,requireActivity())

        binding.btnSave.setOnClickListener {
            if (checkValidations()) {

                val model = UpdateProfile(
                    first_name = binding.etName.text.toString(),
                    last_name = binding.etLastName.text.toString(),
                    email = binding.etEmail.text.toString(),
                    phone = binding.etPhoneNumber.text.toString(),
                    gender = selectedGender,
//                    birthday = binding.etDob.text.toString(),
                    birthday = selectedDate,
                    city = binding.etCity.text.toString(),
                    nationality_id = binding.etNation.text.toString(),
                    device_token = "device-token",
                    device_type = "android"
                )
                updateProfile(model)
            }
        }

    }

    private fun checkValidations(): Boolean {
        val name = binding.etName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhoneNumber.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter your first name", Toast.LENGTH_SHORT).show()
            binding.etName.requestFocus()
            return false
        }
        if (lastName.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter your last name", Toast.LENGTH_SHORT).show()
            binding.etLastName.requestFocus()
            return false
        }

        if (email.isEmpty()) {
            Toast.makeText(requireContext(),"Please enter your email" ,Toast.LENGTH_SHORT).show()
            binding.etEmail.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(requireContext(), "Please enter your valid email", Toast.LENGTH_SHORT).show()
            binding.etEmail.requestFocus()
            return false
        }

        if (phone.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show()
            binding.etPhoneNumber.requestFocus()
            return false
        }
        return true
    }


    private fun showImagePickerBottomSheet() {
        val view = layoutInflater.inflate(R.layout.bottomsheet_open_camera_gallery_box, null)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(view)

        val camera = view.findViewById<TextView>(R.id.openCamera)
        val gallery = view.findViewById<TextView>(R.id.openGallery)

        camera.setOnClickListener {
            checkCameraPermissionAndOpen()
            dialog.dismiss()

        }

        gallery.setOnClickListener {
            galleryLauncher.launch("image/*")
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun getUserDetail() {
        progressBar = AppUtils.progressDialog(requireActivity())
        apiVm.userProfile(prefs.authToken)
            .observe(requireActivity()
            ) { it ->
                println("PankajSingh:$it")
                when (it) {
                    is Resource.Success -> {
                        progressBar?.dismiss()
                        val data = it.data?.payload
                        setUserData(data)
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

                    }
                }
            }
    }

    private fun updateProfile(model:UpdateProfile?) {
        progressBar = AppUtils.progressDialog(requireActivity())
        apiVm.userUpdateProfile(prefs.authToken,model)
            .observe(requireActivity()
            ) { it ->
                println("PankajSingh:$it")
                when (it) {
                    is Resource.Success -> {
                        progressBar?.dismiss()
                        val data = it.data?.payload
                        findNavController().popBackStack()
                        Toast.makeText(requireActivity(), "${it.data?.message}", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error ->{
                        progressBar?.dismiss()
                        when (it.exception) {
                            is NoConnectionException -> { requireActivity().toast("No Internet")
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

                    }
                }
            }
    }



    private fun checkCameraPermissionAndOpen() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (permissions.all {
                ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
            }) {
            imageUri = createImageUri(requireActivity())!!
            cameraLauncher.launch(imageUri)
        } else {
            ActivityCompat.requestPermissions(requireActivity(), permissions, 1001)
        }
    }


    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            imageView.setImageURI(imageUri)
        }
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageView.setImageURI(it)
        }
    }

    fun createImageUri(context: Context): Uri? {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "profile_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        return context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }


    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireActivity(), // use `this` if inside Activity
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, selectedDay)
                val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val dayNameFormatter = SimpleDateFormat("EEEE", Locale.getDefault())

                val formattedDate = formatter.format(selectedCalendar.time)
                val dayName = dayNameFormatter.format(selectedCalendar.time)

                binding.etDob.text = formattedDate
                selectedDate = formattedDate
            },
            year, month, day
        )
        datePickerDialog.show()
    }


    private fun setUserData(data: Payload?) {
        with(binding){
            etName.setText(data?.first_name)
            etLastName.setText(data?.last_name)
            etEmail.setText(data?.email)
            etPhoneNumber.setText(data?.phone)
            etDob.setText(data?.birthday)
            etCity.setText(data?.city)
            etNation.setText(data?.nationality_id)

            data?.gender?.let { gender ->
                val genderOptions = listOf("Male", "Female", "Other")
                val index = genderOptions.indexOfFirst { it.equals(gender, ignoreCase = true) }
                if (index >= 0) {
                    mySpinner.setSelection(index)
                }
            }
        }
    }
}
