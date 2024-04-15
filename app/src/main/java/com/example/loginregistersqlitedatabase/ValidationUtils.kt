package com.example.loginregistersqlitedatabase

import android.text.TextUtils
import android.util.Patterns

object ValidationUtils {

    fun isTextNotEmpty(text:String?):Boolean{
        if(TextUtils.isEmpty(text)) return false
        else return true
    }


    fun isValidEmail(text:String): Boolean{
        return if(TextUtils.isEmpty(text)) false
        else Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }
}