package com.lahsuak.apps.instagramclone.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import com.lahsuak.apps.instagramclone.util.Constants.DATA
import com.lahsuak.apps.instagramclone.util.Constants.USER_STATUS

object UserUtility {
    fun setSharedPref(context: Context, status: Boolean) {
        val editor = context.getSharedPreferences(DATA, MODE_PRIVATE).edit()
        editor.putBoolean(USER_STATUS, status)
        editor.apply()
    }

    fun getSharedPref(context: Context): Boolean {
        val pref = context.getSharedPreferences(DATA, MODE_PRIVATE)
        return pref.getBoolean(USER_STATUS, false)
    }

    fun notifyUser(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}