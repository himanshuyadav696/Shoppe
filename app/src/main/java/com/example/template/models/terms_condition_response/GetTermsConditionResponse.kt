package com.example.template.models.terms_condition_response

data class GetTermsConditionResponse(
    val code: Int,
    val dev_message: String,
    val message: String,
    val payload: Payload,
    val type: String
)