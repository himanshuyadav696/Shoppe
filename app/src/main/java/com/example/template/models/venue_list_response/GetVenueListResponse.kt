package com.example.template.models.venue_list_response

data class GetVenueListResponse(
    val code: Int,
    val dev_message: String,
    val message: String,
    val payload: Payload,
    val type: String
)