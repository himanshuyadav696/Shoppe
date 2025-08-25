package com.example.template.models.venue_list_response

data class Data(
    val amenities: String,
    val available_slots: Int,
    val avg_rating: String,
    val booking_count: Int,
    val city: String,
    val created_at: String,
    val description: String,
    val distance: Double,
    val id: Int,
    val image_urls: String,
    val latitude: String,
    val location: String,
    val longitude: String,
    val min_price: Any,
    val name: String,
    val pin_code: String,
    val state_id: String,
    val updated_at: String
)