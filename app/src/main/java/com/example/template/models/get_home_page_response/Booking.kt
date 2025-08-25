package com.example.template.models.get_home_page_response

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Booking(
    val booking_date: String?=null,
    val booking_notes: String?=null,
    val booking_period: String?=null,
    val booking_reference: String?=null,
    val booking_status: String?=null,
    val booking_type: String?=null,
    val cancellation_reason: String?=null,
    val cancelled_at: String?=null,
    val cancelled_by: String?=null,
    val checked_in_at: String?=null,
    val checked_out_at: String?=null,
    val court_id: Int?=null,
    val court_name: String?=null,
    val created_at: String?=null,
    val discount_amount: String?=null,
    val end_time: String?=null,
    val final_amount: String?=null,
    val id: Int?=null,
    val is_recurring: Int?=null,
    val name: String?=null,
    val paid_services_json: String?=null,
    val parent_booking_id: String?=null,
    val payment_method: String?=null,
    val payment_status: String?=null,
    val recurring_pattern: String?=null,
    val recurring_until: String?=null,
    val slot_price: String?=null,
    val sport_id: String?=null,
    val start_time: String?=null,
    val tax_amount: String?=null,
    val total_amount: String?=null,
    val transaction_id: String?=null,
    val updated_at: String?=null,
    val user_id: String?=null,
    val user_name: String?=null,
    val user_phone: String?=null,
    val vendor_id: Int?=null
): Parcelable
