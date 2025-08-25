package com.example.template.models.get_home_page_response

data class TopVenue(
    val available_slots: Int,
    val avg_rating: String,
    val booking_count: Int,
    val city: String,
    val distance: Double,
    val image_urls: String,
    val latitude: String,
    val location: String,
    val longitude: String,
    val min_price: String,
    val state_id: String,
    val vendor_id: Int,
    val venue_id: Int,
    val venue_name: String
)