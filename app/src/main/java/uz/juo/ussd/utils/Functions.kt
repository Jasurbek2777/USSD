package uz.juo.ussd.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

class Functions {

    fun showDialog(context: Context, name: String, info: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(name)
            .setMessage(info)
        builder.show()
    }
}