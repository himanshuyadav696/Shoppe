package com.example.template.data.network
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
import android.content.Intent
import android.util.Log
import com.example.template.core.network.ApiResponse
import com.example.template.core.network.GetSportResponse
import com.example.template.data.sharedPrefs.PrefsHelper
import com.example.template.models.get_home_page_response.GetHomePageResponse
import com.example.template.models.privacy_policy_response.PrivacyPolicyResponse
import com.example.template.models.terms_condition_response.GetTermsConditionResponse
import com.example.template.ui.login.SignInActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import splitties.init.appCtx
import java.util.concurrent.TimeUnit


interface ApiInterface {

    @POST("userRegister")
    suspend fun registerUser(
        @Body model: RegistrationModel?
    ): Response<ApiResponse>

    @POST("userLogin")
    suspend fun loginUser(
        @Body model: RegistrationModel?
    ): Response<ApiResponse>


    @POST("verifyOtp")
    suspend fun verifyOtp(
        @Body model: RegistrationModel?
    ): Response<ApiResponse>


    @POST("reSendOtp")
    suspend fun reSendOtp(
        @Body model: SendOtpModel?
    ): Response<ApiResponse>


    @GET("home-page")
    suspend fun getHomePage(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<GetHomePageResponse>


    @DELETE("user/deleteAccount")
    suspend fun deleteAccount(
        @Header("auth-token") authToken: String?,
    ): Response<ApiResponse>


    @GET("privacy")
    suspend fun getPrivacyPolicy(
        @Query("type") type: String = "USER"
    ): Response<PrivacyPolicyResponse>

   @GET("terms")
    suspend fun getTermsConditions(
        @Query("type") type: String = "USER"
    ): Response<GetTermsConditionResponse>

    @GET("faqs")
    suspend fun getFaqs(
        @Query("type") type: String = "USER"
    ): Response<ApiResponse>

    @GET("aboutUs")
    suspend fun getAboutUs(): Response<ApiResponse>

    @GET("venues")
    suspend fun getVenueList(
        @Header("auth-token") token: String?,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
        @Query("search") search: String?,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<ApiResponse>


    @GET("venue-details/{id}")
    suspend fun getVenueDetailsById(
        @Header("auth-token") authToken: String?,
        @Path("id") venueId: String?
    ): Response<ApiResponse>

    @POST("user/userLogout")
    suspend fun userLogout(
        @Header("auth-token") authToken: String?,
        ): Response<ApiResponse>

    @POST("user/myProfile")
    suspend fun userProfile(
        @Header("auth-token") authToken: String?,
        ): Response<ApiResponse>

    @POST("user/updateProfile")
    suspend fun userUpdateProfile(
        @Header("auth-token") authToken: String?,
        @Body model: UpdateProfile?=null
    ): Response<ApiResponse>


    @GET("available-slots")
    suspend fun getAvailableSlots(
        @Query("vendor_id") vendorId: Int?,
        @Query("court_id") courtId: Int?,
        @Query("sport_id") sportId: String?,  // nullable if it's empty
        @Query("date") date: String?
    ): Response<ApiResponse>

    @GET("user/paid-services")
    suspend fun getPaidServices(
        @Header("auth-token") token: String?,
        @Query("court_id") courtId: Int? = null,
    ): Response<ApiResponse>


    @GET("user/booking/list")
    suspend fun getBookingList(
        @Header("auth-token") token: String?,
        @Query("filter") filter: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ApiResponse>


    @POST("user/booking/create")
    suspend fun createUserBooking(
        @Body userBookingRequest: UserBookingRequest,
        @Header("auth-token") token: String?,
    ): Response<ApiResponse>

    @POST("user/booking/getBookingDetails")
    suspend fun userBookingDetails(
        @Body request: BookingDetailsRequest,
        @Header("auth-token") token: String?,
    ): Response<ApiResponse>

    @POST("user/booking/split-screen")
    suspend fun userSplitPaymentDetails(
        @Header("auth-token") token: String?,
        @Body request: BookingDetailsRequest,
    ): Response<ApiResponse>


    @POST("user/booking/review")
    suspend fun addRateReview(
        @Header("auth-token") token: String?,
        @Body request: RatingBarRequest
        ): Response<ApiResponse>


    @GET("get-social-contact")
    suspend fun socialContacts(
    ): Response<ApiResponse>

    @GET("customerSupport")
    suspend fun customerSupport(): Response<ApiResponse>



    @POST("get-state")
    suspend fun getStateList(
        @Body model: GetStateModel?
    ): Response<ApiResponse>

    @POST("get-city")
    suspend fun getCityList(
        @Body model: GetCityModel?
    ): Response<ApiResponse>

    @GET("amenities")
    suspend fun getAmmenities(
    ): Response<ApiResponse>


    @POST("vendor/send-otp")
    suspend fun sendOtp(
        @Body model: SendOtpModel?
    ): Response<ApiResponse>


    @POST("vendor/venue")
    suspend fun addVenue(
        @Body model: AddVenueModel?
    ): Response<ApiResponse>


    /*@GET("vendor/venue-details/{id}")
    suspend fun getVenueDetailsById(
        @Header("auth-token") authToken: String?,
        @Path("id") venueId: String?
    ): Response<ApiResponse>
*/
    @GET("sports-list")
    suspend fun getSportList(
    ): Response<GetSportResponse>

    @POST("vendor/add-court")
    suspend fun addCourt(
        @Body model:AddCourt?
    ): Response<ApiResponse>

    @POST("vendor-slots/weekly")
    suspend fun addWeeklySlot(
        @Header("auth-token") authToken: String?,
        @Body model:AddWeeklySlotModel?
    ): Response<ApiResponse>


    @GET("vendor/venues")
    suspend fun getVenues(
        @Header("auth-token") token: String?,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
        @Query("search") search: String?
    ): Response<ApiResponse>


    @POST("vendor/paid-services")
    suspend fun addPaidServices(
        @Body model:AddPaidServices?
    ): Response<ApiResponse>


   /* @GET("vendor/paid-services")
    suspend fun getPaidServices(
        @Header("auth-token") token: String?,
        @Query("court_id") courtId: Int? = null,
    ): Response<ApiResponse>
*/


    @POST("vendor/update-paid-services")
    suspend fun updatePaidServices(
        @Body model: UpdatePaidService?=null
    ): Response<ApiResponse>


    @DELETE("vendor/paid-services/{id}")
    suspend fun deletePaidServices(
        @Header("auth-token") authToken: String?,
        @Path("id") venueId: String?
    ): Response<ApiResponse>

    @GET("vendor/myProfile")
    suspend fun getUserProfile(
        @Header("auth-token") authToken: String?,
    ): Response<ApiResponse>

    @GET("vendor-slots/schedule")
    suspend fun getSlots(
        @Header("auth-token") token: String? = null,
        @Query("court_id") courtId: Int?=null,
        @Query("from") fromDate: String?=null,
        @Query("to") toDate: String?=null
    ): Response<ApiResponse>


    @POST("vendor-slots/overrides")
    suspend fun addNewSlot(
        @Body model: AddSlotRequest?=null
    ): Response<ApiResponse>


    @POST("vendor/updateProfile")
    suspend fun updateProfile(
        @Header("auth-token") authToken: String?,
        @Body model: UpdateProfile?=null
    ): Response<ApiResponse>



    companion object {
//        private const val BASE_URL = "https://floater.brancosoft.co.in/public/api/v1/"
        private const val BASE_URL = "https://floater.brancosoft.co.in/public/api/v1/"

        private const val AUTH = "auth-token"

        fun create(prefsHelper: PrefsHelper): ApiInterface {
            val okHttpClient = OkHttpClient.Builder().apply {
                this.connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES) // write timeout
                    .readTimeout(2, TimeUnit.MINUTES) // read timeout
                    .retryOnConnectionFailure(true)
                addInterceptor {
                    val request: Request.Builder = it.request().newBuilder()
                    request.header("Content-Type", "application/json")
                    request.header("Accept", "application/json")
                    Log.e("TAG", "create:token  ${prefsHelper.authToken}")
                    if (prefsHelper.authToken.isNotEmpty()) {
                        println("AUTH====>:Bearer ${prefsHelper.authToken}")
                        request.header(AUTH,prefsHelper.authToken)
                    }
                    val response = it.proceed(request.build())
                    Log.e("responsecheck", "create: ${response.code}" )
                    if (response.code==401){
                        val intent = Intent(appCtx, SignInActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        prefsHelper.sharedPref.edit().apply{
                            clear()
                            apply()
                        }
                        appCtx.startActivity(intent)
                    }
                    response
                }

              /*  addNetworkInterceptor(chuckerInterceptor)*/

                addNetworkInterceptor(
                    HttpLoggingInterceptor { message -> println(": $message")
                    }.apply { level = HttpLoggingInterceptor.Level.BODY }
                )

            }.build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}