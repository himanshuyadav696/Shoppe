package com.example.template.models.booking_list_response

data class BookingListResponse(
    val code: Int,
    val dev_message: String,
    val message: String,
    val payload: Payload,
    val type: String
) {
    data class Payload(
        val bookings: List<Booking>,
        val pagination: Pagination
    ) {
        data class Booking(
            val booking_date: String,
            val booking_period: String,
            val booking_reference: String,
            val booking_status: String,
            val booking_type: String,
            val court_name: String,
            val end_time: String,
            val final_amount: String,
            val id: Int,
            val slot_price: String,
            val sport_icon: String,
            val sport_name: String,
            val start_time: String,
            val tax_amount: String,
            val total_amount: String,
            val vendor_image: String,
            val vendor_name: String,
            val venue_id: Int,
            val venue_location: String
        )

        data class Pagination(
            val current_page: String,
            val last_page: Int,
            val per_page: String,
            val total: Int
        )
    }
}