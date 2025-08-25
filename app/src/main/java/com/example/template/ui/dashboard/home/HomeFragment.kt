package com.example.template.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.databinding.FragmentHomeBinding
import com.example.template.databinding.RcvFlashSaleBinding
import com.example.template.models.get_home_page_response.Sport
import com.example.template.ui.adapter.FlashSaleAdapter
import com.example.template.ui.adapter.RcvNewItemAdapter
import com.example.template.ui.adapter.RecentlyViewedAdapter
import com.example.template.ui.adapter.StoriesAdapter

class HomeFragment : Fragment(){
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recentlyViewedAdapter: RecentlyViewedAdapter
    private lateinit var storiesAdapter: StoriesAdapter
    private lateinit var rcvNewItemAdapter: RcvNewItemAdapter
    private lateinit var flashSaleAdapter: FlashSaleAdapter
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
        initViews()
        return binding.root
    }

    private fun initViews() {
        loadRcvRecentlyViewed()
        loadRcvStories()
        loadRcvNewItem()
        loadRcvMostPopular()
        loadRcvFlashSale()
    }

    private fun loadRcvMostPopular() {
        val resultList = mutableListOf<Sport>()
        for (i in 0..20) {
            resultList.add(Sport("", i, ""))
        }

        binding.rcvMostPopular.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        flashSaleAdapter = FlashSaleAdapter(
            requireActivity(),
            onItemClick = {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            },
            resultList
        )

        binding.rcvMostPopular.adapter = flashSaleAdapter
    }

    private fun loadRcvFlashSale() {
        val resultList = mutableListOf<Sport>()
        for (i in 0..20) {
            resultList.add(Sport("", i, ""))
        }

        binding.rcvFlashSale.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        flashSaleAdapter = FlashSaleAdapter(
            requireActivity(),
            onItemClick = {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            },
            resultList
        )

        binding.rcvFlashSale.adapter = flashSaleAdapter
    }

    private fun loadRcvRecentlyViewed() {
        val resultList = mutableListOf<Sport>()
        for (i in 0..20) {
            resultList.add(Sport("", i, ""))
        }

        binding.rcvRecentlyViewed.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        recentlyViewedAdapter = RecentlyViewedAdapter(
            requireActivity(),
            onItemClick = {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            },
            resultList
        )

        binding.rcvRecentlyViewed.adapter = recentlyViewedAdapter
    }

    private fun loadRcvStories() {
        val resultList = mutableListOf<Sport>()
        for (i in 0..20) {
            resultList.add(Sport("", i, ""))
        }

        binding.rcvStories.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        storiesAdapter = StoriesAdapter(
            requireActivity(),
            onItemClick = {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            },
            resultList
        )

        binding.rcvStories.adapter = storiesAdapter
    }

    private fun loadRcvNewItem() {
        val resultList = mutableListOf<Sport>()
        for (i in 0..20) {
            resultList.add(Sport("", i, ""))
        }

        binding.rcvNewItems.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        rcvNewItemAdapter = RcvNewItemAdapter(
            requireActivity(),
            onItemClick = {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            },
            resultList
        )

        binding.rcvNewItems.adapter = rcvNewItemAdapter
    }
}