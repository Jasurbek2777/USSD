package uz.juo.ussd

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import uz.juo.ussd.databinding.ActivityMainBinding
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ShareCompat
import androidx.navigation.NavController
import androidx.navigation.ui.*
import com.karumi.dexter.PermissionToken

import com.karumi.dexter.listener.PermissionDeniedResponse

import com.karumi.dexter.listener.PermissionGrantedResponse

import com.karumi.dexter.listener.single.PermissionListener

import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequest
import uz.juo.ussd.models.Internetpaketlar
import uz.juo.ussd.models.SmsPaketlar
import java.util.*
import kotlin.collections.ArrayList
import uz.juo.ussd.ui.language.LanguageActivity


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.appBarMain.inner.homeBtn.visibility = View.VISIBLE
        binding.appBarMain.inner.bottomNav.visibility = View.VISIBLE
        var count = 0
        binding.apply {
            appBarMain.inner.balans.setOnClickListener {
                Dexter.withContext(applicationContext)
                    .withPermission(Manifest.permission.CALL_PHONE)
                    .withListener(object : PermissionListener {
                        override fun onPermissionGranted(response: PermissionGrantedResponse) {
                            val callIntent = Intent(Intent.ACTION_CALL)
                            var hash = Uri.encode("#")
                            callIntent.data = Uri.parse("tel:*100$hash")
                            startActivity(callIntent)
                        }

                        override fun onPermissionDenied(response: PermissionDeniedResponse) {
                            if (count != 1) {
                                Toast.makeText(
                                    applicationContext,
                                    "Please grant the permission !",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "You did not grant the permission please manually grand it!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                val uri = Uri.fromParts("package", packageName, null)
                                intent.data = uri
                                startActivity(intent)
                            }

                        }

                        override fun onPermissionRationaleShouldBeShown(
                            permission: PermissionRequest?,
                            token: PermissionToken?
                        ) {
                            token?.continuePermissionRequest()
                            count++

                        }
                    }).check()
            }
            appBarMain.inner.operator.setOnClickListener {
                val url = "https://scc.uzmobile.uz/ps/scc/login.php?P_USER_LANG_ID=4"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
            appBarMain.inner.setting.setOnClickListener {
                navController.popBackStack()
//                appBarMain.inner.homeBtn.visibility = View.INVISIBLE
//                appBarMain.inner.bottomNav.visibility = View.INVISIBLE
                var intent = Intent(applicationContext,LanguageActivity::class.java)
                startActivity(intent)
            }
            appBarMain.inner.news.setOnClickListener {
                if (navController.currentDestination!!.id != R.id.newsFragment2) {
                    navController.navigate(R.id.newsFragment2)
                }

            }
            appBarMain.inner.homeBtn.setOnClickListener {
                while (navController.currentDestination!!.id != R.id.nav_home){
                    navController.popBackStack()
                }
            }
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        binding.navView.setNavigationItemSelectedListener {
            binding.navView.menu.getItem(0).isChecked = true
            when (it.itemId) {
                R.id.drawer_channel -> {
                    val uri = Uri.parse("https://t.me/ussduz")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    try {
                        startActivity(intent)
                    } catch (e: Exception) {
                        binding.drawerLayout.close()
                    }

                }
                R.id.drawer_contact -> {
                    val dialog = AlertDialog.Builder(this)
                    dialog.setTitle(R.string.biz_blan_aloqa)
                    dialog.setMessage("Email: ussdmobile@gamil.com")
                    dialog.setPositiveButton(R.string.send_email) { dialog, id ->
                        val intent = Intent(Intent.ACTION_SENDTO)
                        intent.data = Uri.parse("mailto:")
                        intent.putExtra(Intent.EXTRA_EMAIL, "ussdmobile@gamil.com")
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                        if (intent.resolveActivity(packageManager) != null) startActivity(intent)
                    }
                    dialog.setNegativeButton(R.string.back) { dialog, id ->
                        binding.drawerLayout.close()
                    }
                    val alertDialog = dialog.create()
                    alertDialog.show()

                }
                R.id.drawer_share -> {
                    ShareCompat.IntentBuilder.from(this@MainActivity)
                        .setType("text/plain")
                        .setText("http://play.google.com/store/apps/details?id=uz.pdp.uzmobile")
                        .startChooser()

                }
                R.id.drawer_rate -> {
                    val uri = Uri.parse("market://details?id=" + this@MainActivity.packageName)
                    val goToMarket = Intent(Intent.ACTION_VIEW, uri)
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                    try {
                        startActivity(goToMarket)
                    } catch (e: ActivityNotFoundException) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=uz.pdp.uzmobile")
                            )
                        )
                    }

                }
                R.id.drawer_info -> {

                    val dialog = AlertDialog.Builder(this)
                    dialog.setTitle(R.string.about_us)
                    dialog.setMessage(R.string.about_txt)
                    dialog.setPositiveButton(R.string.back,
                        DialogInterface.OnClickListener { dialog, id ->


                        })
                    val alertDialog = dialog.create()
                    alertDialog.show()

                }
            }
            true
        }
        navView.itemIconTintList = null
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}