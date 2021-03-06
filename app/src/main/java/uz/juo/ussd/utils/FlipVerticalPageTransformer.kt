package uz.juo.ussd.utils

import android.view.View
import androidx.viewpager.widget.ViewPager

class FlipVerticalPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, pos: Float) {
        val rotation = -180f * pos
        page.alpha = if (rotation > 90f || rotation < -90f) 0f else 1f
        page.pivotX = page.width * 0.5f
        page.pivotY = page.height * 0.5f
        page.rotationX = rotation
    }
}