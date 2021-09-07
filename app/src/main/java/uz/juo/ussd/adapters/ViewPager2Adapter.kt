package uz.juo.ussd.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.juo.ussd.ui.packages.PackageInnerFragment

class ViewPager2Adapter(var category: String, fa: FragmentActivity) :
    FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        PackageInnerFragment.newInstance(category, position.toString())


}