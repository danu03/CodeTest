package com.danusuhendra.codingtestperintis.data

import android.content.Context
import com.danusuhendra.codingtestperintis.utils.PASSWORD
import com.danusuhendra.codingtestperintis.utils.USERNAME

class PrefHelper(private val context: Context) {

    private val PREF_NAME = "suit-pref"
    private val PRIVATE_MODE = 0

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    var username: String?
        get() {
            return sharedPreferences.getString(USERNAME, null)
        }
        set(value) {
            sharedPreferences.edit().apply {
                putString(USERNAME, value)
                apply()
            }
        }

    var password: String?
        get() {
            return sharedPreferences.getString(PASSWORD, null)
        }
        set(value) {
            sharedPreferences.edit().apply {
                putString(PASSWORD, value)
                apply()
            }
        }
}