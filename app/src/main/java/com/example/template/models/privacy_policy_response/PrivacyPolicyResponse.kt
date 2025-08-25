package com.example.template.models.privacy_policy_response

data class PrivacyPolicyResponse(
    val code: Int,
    val dev_message: String,
    val message: String,
    val payload: Payload,
    val type: String
)