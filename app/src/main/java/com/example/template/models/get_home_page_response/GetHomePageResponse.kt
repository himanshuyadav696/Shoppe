package com.example.template.models.get_home_page_response

data class GetHomePageResponse(
    val code: Int,
    val dev_message: String,
    val message: String,
    val payload: Payload,
    val type: String
)