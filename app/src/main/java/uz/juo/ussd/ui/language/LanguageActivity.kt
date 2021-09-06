package uz.juo.ussd.ui.language

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yariksoffice.lingver.Lingver
import uz.juo.ussd.App.Companion.LANGUAGE_ENGLISH
import uz.juo.ussd.App.Companion.LANGUAGE_ENGLISH_COUNTRY
import uz.juo.ussd.App.Companion.LANGUAGE_RUSSIAN
import uz.juo.ussd.App.Companion.LANGUAGE_RUSSIAN_COUNTRY
import uz.juo.ussd.App.Companion.LANGUAGE_UZBEK
import uz.juo.ussd.App.Companion.LANGUAGE_UZBEK_COUNTRY
import uz.juo.ussd.MainActivity
import uz.juo.ussd.databinding.ActivityLanguageBinding
import uz.juo.ussd.utils.SharedPreference

class LanguageActivity : AppCompatActivity() {
    lateinit var binding: ActivityLanguageBinding
    val language1 = SharedPreference.getInstance(this@LanguageActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ruTv.setOnClickListener {
                language1.lang="ru"
                setNewLocale(LANGUAGE_RUSSIAN, LANGUAGE_RUSSIAN_COUNTRY)
            }
            uzTv.setOnClickListener {
                setNewLocale(LANGUAGE_ENGLISH, LANGUAGE_ENGLISH_COUNTRY)
                language1.lang="en"
            }
            cirillTv.setOnClickListener {
                setNewLocale(LANGUAGE_UZBEK, LANGUAGE_UZBEK_COUNTRY)
                language1.lang="uz"
            }
        }
    }

    private fun setNewLocale(language: String, country: String) {
        language1.setHasLang(true)
        Lingver.getInstance().setLocale(this, language, country)
        restart()
    }

    private fun restart() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

}