package com.example.template.data.apiRepo

import AddCourt
import AddPaidServices
import AddSlotRequest
import AddVenueModel
import AddWeeklySlotModel
import BookingDetailsRequest
import GetCityModel
import GetStateModel
import RatingBarRequest
import RegistrationModel
import SendOtpModel
import UpdatePaidService
import UpdateProfile
import UserBookingRequest
import com.example.template.core.network.BaseRepo
import com.example.template.data.network.ApiInterface
import com.example.template.data.sharedPrefs.PrefsHelper

class apiRepo(private val apiInterface: ApiInterface, private val prefsHelper: PrefsHelper):
    BaseRepo() {

    private fun getBearerToken() = """Bearer ${prefsHelper.authToken}"""

    suspend fun registerUser(model: RegistrationModel?) =
        loadData { apiInterface.registerUser(model) }

    suspend fun loginUser(model: RegistrationModel?) =
        loadData { apiInterface.loginUser(model) }

    suspend fun verifyOtp(model: RegistrationModel?) =
        loadData { apiInterface.verifyOtp(model) }

    suspend fun reSendOtp(model: SendOtpModel?) =
        loadData { apiInterface.reSendOtp(model) }

    suspend fun getHomePage(latitude: Double, longitude: Double) =
        loadData { apiInterface.getHomePage(latitude, longitude) }

    suspend fun getDeleteAccount(authToken: String) =
        loadData { apiInterface.deleteAccount(authToken) }

    suspend fun getPrivacyPolicy() =
        loadData { apiInterface.getPrivacyPolicy() }

    suspend fun getTermsConditions() =
        loadData { apiInterface.getTermsConditions() }

    suspend fun getFaqs() =
        loadData { apiInterface.getFaqs() }

    suspend fun getAboutsUs() =
        loadData { apiInterface.getAboutUs() }

    suspend fun getVenuesList(authToken: String, perPage: Int, page: Int, search: String?, latitude: Double, longitude: Double) =
        loadData { apiInterface.getVenueList(authToken,perPage,page,search,latitude,longitude) }

    suspend fun getVenueDetailById(token:String?,venueId:String?) =
        loadData { apiInterface.getVenueDetailsById(token,venueId) }

    suspend fun userLogout(authToken: String) =
        loadData { apiInterface.userLogout(authToken) }

    suspend fun userProfile(authToken: String) =
        loadData { apiInterface.userProfile(authToken) }

    suspend fun userUpdateProfile(authToken: String,model:UpdateProfile?) =
        loadData { apiInterface.userUpdateProfile(authToken,model) }

    suspend fun getAvailableSlots(vendorId:Int?,courtId:Int?,sportId:String?, fromDate:String) =
        loadData { apiInterface.getAvailableSlots(vendorId, courtId,sportId,fromDate) }

    suspend fun getBookingList(token:String?,filter: String,perPage: Int,page: Int) =
        loadData { apiInterface.getBookingList(token,filter,perPage,page) }

    suspend fun createUserBooking(model:UserBookingRequest, token:String?) =
        loadData { apiInterface.createUserBooking(model,token) }

    suspend fun userBookingDetails(request: BookingDetailsRequest, token:String?) =
        loadData { apiInterface.userBookingDetails(request,token) }

    suspend fun userSplitPaymentDetails(token:String?, request: BookingDetailsRequest) =
        loadData { apiInterface.userSplitPaymentDetails(token,request) }

    suspend fun addRateReview(token:String?,request: RatingBarRequest) =
        loadData { apiInterface.addRateReview(token,request) }

    suspend fun socialContacts() =
        loadData { apiInterface.socialContacts()
        }
    suspend fun customerSupport() =
        loadData { apiInterface.customerSupport()
        }



    suspend fun sendOtp(model: SendOtpModel?) =
        loadData { apiInterface.sendOtp(model) }

    suspend fun getStateList(model: GetStateModel?) =
        loadData { apiInterface.getStateList(model) }


    suspend fun getCityList(model: GetCityModel?) =
        loadData { apiInterface.getCityList(model) }

    suspend fun getAmmenties() =
        loadData { apiInterface.getAmmenities() }

    suspend fun addVenue(model: AddVenueModel?) =
        loadData { apiInterface.addVenue(model) }


    suspend fun getSportList() =
        loadData { apiInterface.getSportList() }

    suspend fun addCourt(model: AddCourt?) =
        loadData { apiInterface.addCourt(model) }

    suspend fun addWeeklySlot(token:String,model: AddWeeklySlotModel?) =
        loadData { apiInterface.addWeeklySlot(token,model) }


    suspend fun getVenueList(token:String?,perPage: Int,page: Int,search: String?) =
        loadData { apiInterface.getVenues(token,perPage,page,search) }

    suspend fun addPaidServices(model: AddPaidServices?) =
        loadData { apiInterface.addPaidServices(model) }

    suspend fun getPaidServices(token:String?,court_id:Int?) =
        loadData { apiInterface.getPaidServices(token,court_id) }


    suspend fun updatePaidServices(model:UpdatePaidService?) =
        loadData { apiInterface.updatePaidServices(model) }

    suspend fun deletePaidServices(token:String?,id:String?) =
        loadData { apiInterface.deletePaidServices(token,id) }

    suspend fun getUserProfile(token:String?) =
        loadData { apiInterface.getUserProfile(token) }

    suspend fun getSlots(token:String?,courtId:Int?,fromDate:String?,toDate:String?) =
        loadData { apiInterface.getSlots(token, courtId, fromDate, toDate) }

    suspend fun addNewSlot(model:AddSlotRequest?) =
        loadData { apiInterface.addNewSlot(model) }


    suspend fun updateProfile(token:String?,model:UpdateProfile?) =
        loadData { apiInterface.updateProfile(token,model) }

}
