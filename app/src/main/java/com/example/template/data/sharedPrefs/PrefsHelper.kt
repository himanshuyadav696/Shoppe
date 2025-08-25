package com.example.template.data.sharedPrefs

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences

class PrefsHelper(context: Context) {
    /* Init sharedPreferences with injected context*/
    val sharedPref = getDefaultSharedPreferences(context)
    var token by PrefsStringDelegate(PrefKeys.USER_TOKEN)
    var isLoggedIn by PrefsBooleanDelegate(PrefKeys.IS_LOGIN)
    var isFirstTime by PrefsBooleanDelegate(PrefKeys.IS_FIRST_TIME)
    
    var authToken by PrefsStringDelegate(PrefKeys.AUTH_TOKEN)
    var createdAt by PrefsStringDelegate(PrefKeys.CREATED_AT)
    var deviceToken by PrefsStringDelegate(PrefKeys.DEVICE_TOKEN)
    var deviceType by PrefsStringDelegate(PrefKeys.DEVICE_TYPE)
    var email by PrefsStringDelegate(PrefKeys.EMAIL)
    var userId by PrefsIntDelegate(PrefKeys.USER_ID)
    var name by PrefsStringDelegate(PrefKeys.NAME)
    var phone by PrefsStringDelegate(PrefKeys.PHONE)
    var profileImage by PrefsStringDelegate(PrefKeys.PROFILE_IMG)
    var status by PrefsIntDelegate(PrefKeys.STATUS)


}


object PrefKeys {
    const val IS_LOGIN = "UESR_iS_LOGIN"
    const val USER_TOKEN = "UESR_TOKEN"
    const val IS_FIRST_TIME = "IS_FIRST_TIME"
    
    const val AUTH_TOKEN = "AUTH_TOKEN"
    const val CREATED_AT = "CREATED_AT"
    const val DEVICE_TOKEN = "DEVICE_TOKEN"
    const val DEVICE_TYPE = "DEVICE_TYPE"
    const val EMAIL = "EMAIL"
    const val USER_ID = "USER_ID"
    const val NAME = "NAME"
    const val PHONE = "PHONE"
    const val PROFILE_IMG = "PROFILE_IMG"
    const val STATUS = "STATUS"



}
