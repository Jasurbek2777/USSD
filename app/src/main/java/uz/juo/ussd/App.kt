package uz.juo.ussd

import android.app.Application
import com.yariksoffice.lingver.Lingver
import com.yariksoffice.lingver.store.PreferenceLocaleStore
import java.util.*

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        val store = PreferenceLocaleStore(this, Locale(LANGUAGE_UZBEK))
        val lingver = Lingver.init(this, store)
    }

    companion object {
        const val LANGUAGE_UZBEK = "uz"
        const val LANGUAGE_UZBEK_COUNTRY = "UZ"
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_ENGLISH_COUNTRY = "US"
        const val LANGUAGE_RUSSIAN = "ru"
        const val LANGUAGE_RUSSIAN_COUNTRY = "RU"
    }
}