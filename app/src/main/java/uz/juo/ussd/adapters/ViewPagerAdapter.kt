package uz.juo.ussd.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import uz.juo.ussd.R
import uz.juo.ussd.databinding.HomeViewpagerItemBinding
import uz.juo.ussd.models.Tariflar

class ViewPagerAdapter(var images: ArrayList<Tariflar>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
        var item = view.inflate(R.layout.viewpager_layout, container, false)
//        var image : ImageView= item.findViewById(R.id.image)
//        image.setImageResource(images[position])
        container.addView(item)
        return item
    }

    override fun getCount(): Int {
        return 5
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}