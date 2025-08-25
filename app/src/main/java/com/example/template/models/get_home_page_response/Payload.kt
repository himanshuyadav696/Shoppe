package com.example.template.models.get_home_page_response

import VenueData

data class Payload(
    val banners: List<Banner>,
    val sports: List<Sport>,
    val top_venues: List<VenueData>
)