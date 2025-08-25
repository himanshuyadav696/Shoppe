package com.example.template.ui.dashboard.home

import Bookings
import IntroSlideData
import UpComingBookingAdapter
import VenueData
import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.template.R
import com.example.template.core.data.Resource
import com.example.template.core.exception.NoConnectionException
import com.example.template.data.apiVm.apiVm
import com.example.template.data.sharedPrefs.PrefsHelper
import com.example.template.databinding.FragmentHomeBinding
import com.example.template.ui.adapter.ActivityAdapter
import com.example.template.ui.adapter.BannerAdapter
import com.example.template.ui.adapter.VenueListAdapter
import com.example.template.utils.AppUtils
import com.example.template.utils.extensions.getError
import com.example.template.utils.extensions.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(),UpComingBookingAdapter.OnItemClickListner, VenueListAdapter.OnItemClickListener, ActivityAdapter.onItemClickListner{
    private lateinit var binding: FragmentHomeBinding
    private var currentPage = 0
    private val autoScrollDelay = 3000L
    private val prefs by inject<PrefsHelper>()
    private var progresbar: Dialog? = null
    private val apiVm by viewModel<apiVm>()
    private lateinit var bannerAdapter: BannerAdapter
//    private lateinit var topClubAdapter: VenueListAdapter

    private val topClubAdapter by lazy {

        VenueListAdapter(requireActivity(),this)
    }

    private lateinit var activityAdapter: ActivityAdapter
    private lateinit var upComingBookingAdapter: UpComingBookingAdapter
    var data = mutableListOf<IntroSlideData>(
        IntroSlideData("My Calendar\n ", "", R.drawable.calendar_month),
        IntroSlideData("My Bookings\n ", "", R.drawable.check_circle),
        IntroSlideData("Pending\n" + "Bookings", "", R.drawable.ic_pending),
        IntroSlideData("Total\n" + "Revenues", "", R.drawable.insert_chart),
        IntroSlideData("Add New\nVenue", "", R.drawable.plus),
        IntroSlideData("View Venue \n ", "", R.drawable.ic_eye))

    var sampleData = mutableListOf<IntroSlideData>(
    IntroSlideData("Football","", R.drawable.baller_image4),
    IntroSlideData("Foot","", R.drawable.baller_image4),
    IntroSlideData("Foot","", R.drawable.baller_image4),
    IntroSlideData("Dyce","", R.drawable.baller_image4),
    )
    private val introPages = listOf(
        IntroSlideData("Add Your Venue", "Showcase your turf with photos, rates, time slots, and more.", R.drawable.banner_user),
        IntroSlideData("Add Your Venue", "Showcase your turf with photos, rates, time slots, and more.", R.drawable.banner_user),
        IntroSlideData("Add Your Venue", "Showcase your turf with photos, rates, time slots, and more.", R.drawable.banner_user)
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
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        try {
            getHomePage()
        }catch (e: Exception){
            Log.e("TAG","${e.message}")
        }

    }
    private fun initViews() {
//        binding.tvUserName.text = prefs.name
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1001
            )
        }

        bannerAdapter = BannerAdapter(emptyList())
        binding.bannerViewPager.adapter = bannerAdapter
        binding.dotsIndicator.setViewPager2(binding.bannerViewPager)


        /* val adapter = BannerAdapter(introPages)
         binding.bannerViewPager.adapter = adapter
         binding.dotsIndicator.setViewPager2(binding.bannerViewPager)
        */
        startAutoScroll()
        loadRcvSport()
        loadRcvBooking()
    }

   /* private fun setupBannerCarousel(bannerList: List<Banner>) {
        bannerAdapter = BannerAdapter(bannerList)
        binding.bannerViewPager.adapter = bannerAdapter
        binding.dotsIndicator.setViewPager2(binding.bannerViewPager)
    }
*/

    private fun loadRcvSport() {
        binding.rcvSports.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        activityAdapter = ActivityAdapter(requireActivity(), this)
        binding.rcvSports.adapter = activityAdapter
    }

    private fun loadRcvBooking(){
        binding.rcvClubList.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)

        //using lazy
//        topClubAdapter = VenueListAdapter(requireActivity(),this)
        binding.rcvClubList.adapter = topClubAdapter

        /*
        var setList = mutableListOf<IntroSlideData>()
        for(i in 1..5){
            setList.add(IntroSlideData("Cricket","",R.drawable.banner))
        }
        topClubAdapter.setData(setList)
*/

      /*  val venueDataList = mutableListOf<VenueData>()

        for (i in 1..5) {
            venueDataList.add(
                VenueData(
                    amenities = "Amenities $i",
                    city = "City $i",
                    created_at = "2025-07-14T10:0${i}:00",
                    description = "This is a description for venue $i.",
                    id = i,
                    image_urls = "https://example.com/image_$i.jpg",
                    latitude = "28.60$i",
                    location = "Location $i",
                    longitude = "77.20$i",
                    name = "Ground $i",
                    pin_code = "1100$i",
                    state_id = "$i",
                    updated_at = "2025-07-14T12:0${i}:00"
                )
            )
        }
        topClubAdapter.setData(venueDataList)
*/
    }

    private fun getHomePage() {
        progresbar = AppUtils.progressDialog(requireActivity())
        var lat =28.540928
        var long =77.463552
        apiVm.getHomePage(lat, long).observe(requireActivity()
            ) { it ->
                println("PankajSingh:$it")
                when (it) {
                    is Resource.Success -> {
                        val data = it.data?.payload

                        try {
                            val banners = it.data?.payload?.banners ?: emptyList()
                            bannerAdapter.setItems(banners) // if adapter already initialized

                            val sportList = it.data?.payload?.sports ?: emptyList()
                            activityAdapter.setData(sportList) // if adapter already initialized

                            val topSportList = it.data?.payload?.top_venues ?: emptyList()
                            topClubAdapter.setData(topSportList)

                            Toast.makeText(
                                requireActivity(),
                                "${it.data?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }catch (e: Exception){
                            Log.d("Tag","InitViewdjd :${e.message}").toString()

                        }
                        Log.d("Tag","InitViewdjd :$data").toString()
                        /*if(it.data?.code==1){
//                            countdown?.cancel()
                            val intent = Intent(requireActivity(),MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        }*/
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
                    else -> {

                    }
                }
            }
    }



    private fun startAutoScroll() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val itemCount = binding.bannerViewPager.adapter?.itemCount ?: return

                if (itemCount == 0) {
                    handler.removeCallbacks(this) // stop scrolling if no items
                    return
                }

                currentPage = (binding.bannerViewPager.currentItem + 1) % itemCount
                binding.bannerViewPager.setCurrentItem(currentPage, true)
                handler.postDelayed(this, autoScrollDelay)
            }
        }

        // Start the initial auto-scroll
        handler.postDelayed(runnable, autoScrollDelay)

        // Optional: Update current page manually if user swipes
        binding.bannerViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
    }

    private fun startAutoScrolls() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val itemCount = binding.bannerViewPager.adapter?.itemCount ?: return
                currentPage = (binding.bannerViewPager.currentItem + 1) % itemCount
                binding.bannerViewPager.setCurrentItem(currentPage, true)
                handler.postDelayed(this, autoScrollDelay)
            }
        }
        handler.postDelayed(runnable, autoScrollDelay)
        // Optional: stop auto-scroll on touch interaction
        binding.bannerViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
    }

    override fun onItemClicked(city: IntroSlideData, position: Int) {
        when(position){
            0->{
//                findNavController().navigate(R.id.createSlotsFragment)
              //  requireActivity().toast("Work in progress")
            }
            1 ->{
                requireActivity().toast("Work in progress")
            }
            2 ->{
                requireActivity().toast("Work in progress")
            }
            3 ->{
                requireActivity().toast("Work in progress")
            }
            4 ->{
//                findNavController().navigate(R.id.addVenueFragment)
            }
            5->{
                requireActivity().toast("Work in progress")
            }
        }
        Log.e("TAG", "onItemClicked:sddd  $position", )
    }

    /*override fun onItemClick(
        data: TopVenue,
        position: Int
    ) {
        findNavController().navigate(R.id.venueDetailFragment)
    }
*/
    override fun onItemClick(data: VenueData, position: Int) {
        val bundle = Bundle()
        bundle.putString("venueId",data.venue_id.toString())
//        findNavController().navigate(R.id.venueDetailFragment, bundle)

    }


   /* override fun onItemClicked(
        data: Booking,
        position: Int,
        type: String
    ) {

    }
*/
    override fun onItemClicked(data: Bookings, position: Int, type: String) {

    }


}