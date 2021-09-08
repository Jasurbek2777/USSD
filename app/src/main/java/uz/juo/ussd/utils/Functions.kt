package uz.juo.ussd.utils

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import uz.juo.ussd.R

class Functions {

    fun showDialog(context: Context, name: String, info: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(name)
            .setMessage(info)
            .setPositiveButton(context.getString(R.string.back)
            ) { dialog, which ->
                dialog.cancel()
            }
        builder.show()
    }

//    fun checkBalans(context: Context, packageName: String, count: Int) {
//        Dexter.withContext(context)
//            .withPermission(Manifest.permission.CALL_PHONE)
//            .withListener(object : PermissionListener {
//                override fun onPermissionGranted(response: PermissionGrantedResponse) {
//                    val callIntent = Intent(Intent.ACTION_CALL)
//                    var hash = Uri.encode("#")
//                    callIntent.data = Uri.parse("tel:*100$hash")
//                    ContextCompat.startActivity(callIntent)
//                }
//
//                override fun onPermissionDenied(response: PermissionDeniedResponse) {
//                    if (count != 1) {
//                        Toast.makeText(
//                            context,
//                            "Please grant the permission !",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    } else {
//                        Toast.makeText(
//                            context,
//                            "You did not grant the permission please manually grand it!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                        val uri = Uri.fromParts("package", packageName, null)
//                        intent.data = uri
//                        startActivity(intent)
//                    }
//
//                }
//
//                override fun onPermissionRationaleShouldBeShown(
//                    permission: PermissionRequest?,
//                    token: PermissionToken?
//                ) {
//                    token?.continuePermissionRequest()
//                    count++
//
//                }
//            }).check()
//    }
}