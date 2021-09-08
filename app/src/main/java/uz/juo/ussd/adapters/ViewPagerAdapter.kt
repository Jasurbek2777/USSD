package uz.juo.ussd.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import uz.juo.ussd.R
import uz.juo.ussd.databinding.HomeViewpagerItemBinding
import uz.juo.ussd.models.Tariflar

class ViewPagerAdapter(var images: ArrayList<Tariflar>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
        var item = view.inflate(R.layout.viewpager_layout, container, false)
        var name: TextView = item.findViewById(R.id.name_tv12)
        var price: TextView = item.findViewById(R.id.price)
        var tv1: TextView = item.findViewById(R.id.tv1)
        var tv2: TextView = item.findViewById(R.id.tv2)
        var tv3: TextView = item.findViewById(R.id.tv3)
        name.text = images[position].name
        price.text = images[position].sum_mont
        tv1.text = images[position].month_min
        tv2.text = images[position].mont_mb
        tv3.text = images[position].month_sms

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