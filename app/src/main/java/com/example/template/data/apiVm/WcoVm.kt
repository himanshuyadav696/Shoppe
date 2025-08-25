package com.example.template.data.apiVm
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.template.data.apiRepo.apiRepo


class apiVm (private val apiRepo: apiRepo): ViewModel() {

    fun registerUser(model : RegistrationModel?) =
        liveData { emit(apiRepo.registerUser(model).value) }

    fun loginUser(model : RegistrationModel?) =
        liveData { emit(apiRepo.loginUser(model).value) }

    fun verifyOtp(model : RegistrationModel?) =
        liveData {
            emit(apiRepo.verifyOtp(model).value) }

    fun reSendOtp(model : SendOtpModel?) =
        liveData {
            emit(apiRepo.reSendOtp(model).value) }
    fun getHomePage(lat : Double, long: Double) =
        liveData {
            emit(apiRepo.getHomePage(lat,long).value) }

    fun getDeleteAccount(authToken: String) =
        liveData {
            emit(apiRepo.getDeleteAccount(authToken).value) }

    fun getPrivacyPolicy() =
        liveData {
            emit(apiRepo.getPrivacyPolicy().value) }

    fun getTermsConditions() =
        liveData {
            emit(apiRepo.getTermsConditions().value) }
    fun getFaqs() =
        liveData {
            emit(apiRepo.getFaqs().value) }

    fun getAboutsUs() =
        liveData { emit(apiRepo.getAboutsUs().value) }

    fun getVenuesList(authToken: String, perPage: Int, page: Int, search: String?, lat: Double, long: Double) =
        liveData { emit(apiRepo.getVenuesList(authToken,perPage,page,search,lat,long).value) }

    fun getVenueDetailById(token:String?,venueId:String?) =
        liveData { emit(apiRepo.getVenueDetailById(token,venueId).value) }

    fun userLogout(authToken: String) =
        liveData { emit(apiRepo.userLogout(authToken).value) }

    fun userProfile(authToken: String) =
        liveData { emit(apiRepo.userProfile(authToken).value) }

    fun userUpdateProfile(authToken: String, model:UpdateProfile?) =
        liveData { emit(apiRepo.userUpdateProfile(authToken, model).value) }


    fun getAvailableSlots(vendorId: Int?, courtId:Int?, sportId: String?,fromDate:String) =
        liveData { emit(apiRepo.getAvailableSlots(vendorId,courtId,sportId,fromDate).value) }

    fun getBookingList(authToken: String, filter: String, perPage: Int, page: Int) =
        liveData { emit(apiRepo.getBookingList(authToken,filter, perPage,page).value) }

    fun createUserBooking(request:UserBookingRequest, authToken: String) =
        liveData { emit(apiRepo.createUserBooking(request,authToken).value) }

    fun userBookingDetails(request:BookingDetailsRequest, authToken: String) =
        liveData { emit(apiRepo.userBookingDetails(request,authToken).value) }

    fun userSplitPaymentDetails(authToken: String, request:BookingDetailsRequest,) =
        liveData { emit(apiRepo.userSplitPaymentDetails(authToken,request).value) }

    fun addRateReview(authToken: String, request: RatingBarRequest) =
        liveData { emit(apiRepo.addRateReview(authToken,request).value) }

    fun socialContacts() =
        liveData { emit(apiRepo.socialContacts().value) }

    fun customerSupport() =
        liveData { emit(apiRepo.customerSupport().value) }


    fun sendOtp(model : SendOtpModel?) =
        liveData { emit(apiRepo.sendOtp(model).value) }


    fun getStateList(model : GetStateModel?) =
        liveData { emit(apiRepo.getStateList(model).value) }

    fun getCityList(model : GetCityModel?) =
        liveData { emit(apiRepo.getCityList(model).value) }


    fun getAmmenties() =
        liveData { emit(apiRepo.getAmmenties().value) }

    fun addVenue(model : AddVenueModel?) =
        liveData { emit(apiRepo.addVenue(model).value) }



    fun getSportsList() =
        liveData { emit(apiRepo.getSportList().value) }

    fun addCourt(model : AddCourt?) =
        liveData { emit(apiRepo.addCourt(model).value) }

    fun addWeeklySlot(token:String,model : AddWeeklySlotModel?) =
        liveData { emit(apiRepo.addWeeklySlot(token,model).value) }

    fun getVenueList(token:String?,perPage: Int,page: Int,search: String?) =
        liveData { emit(apiRepo.getVenueList(token,perPage,page,search).value) }


    fun addPaidServices(model : AddPaidServices?) =
        liveData { emit(apiRepo.addPaidServices(model).value) }

    fun getPaidServices(token:String,courtId:Int?) =
        liveData { emit(apiRepo.getPaidServices(token,courtId).value) }


    fun updatePaidServices(model:UpdatePaidService?) =
        liveData { emit(apiRepo.updatePaidServices(model).value) }

    fun deletePaidServices(token:String?,id:String?) =
        liveData { emit(apiRepo.deletePaidServices(token,id).value) }

    fun getUserDetail(token:String?) =
        liveData { emit(apiRepo.getUserProfile(token).value) }

    fun getSlots(token:String?,courtId:Int?,fromDate:String?,toDate:String?) =
        liveData { emit(apiRepo.getSlots(token,courtId,fromDate,toDate).value) }

    fun addNewSlot(model:AddSlotRequest?) =
        liveData { emit(apiRepo.addNewSlot(model).value) }

    fun updateProfile(token:String?,model:UpdateProfile?) =
        liveData { emit(apiRepo.updateProfile(token,model).value) }
}