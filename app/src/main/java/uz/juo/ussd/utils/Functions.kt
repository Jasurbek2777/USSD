package uz.juo.ussd.utils

import android.content.Context

class Functions {
    fun hasLanguage(context: Context) {
        SharedPreference.getInstance(context).haslang
    }
    fun getLang(context: Context){
        SharedPreference.getInstance(context).lang
    }
}