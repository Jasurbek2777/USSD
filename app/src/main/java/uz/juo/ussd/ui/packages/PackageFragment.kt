package uz.juo.ussd.ui.packages

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import uz.juo.ussd.MainActivity
import uz.juo.ussd.R
import uz.juo.ussd.adapters.ViewPager2Adapter
import uz.juo.ussd.databinding.FragmentPackageBinding
import uz.juo.ussd.databinding.TabItemBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PackageFragment : Fragment() {
    lateinit var categoryList: ArrayList<String>
    private var param1: String? = null
    private var param2: String? = null
    var db=FirebaseFirestore.getInstance()
    lateinit var binding: FragmentPackageBinding
    lateinit var viewpagerAdapter: ViewPager2Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = param1
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPackageBinding.inflate(inflater, container, false)
        (activity as MainActivity).supportActionBar?.title = param1
        viewpagerAdapter = ViewPager2Adapter(param1.toString(), requireActivity())
        loadCategory()
        binding.viewpager.adapter = viewpagerAdapter
        TabLayoutMediator(binding.tab, binding.viewpager) { tab, i ->
            tab.text = categoryList[i]
        }.attach()
        setTabs()
        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab!!.customView
                val bind = TabItemBinding.bind(customView!!)
                bind.linear.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.tab_item_round2)
                bind.itemTv.setTextColor(Color.parseColor("#01B4FF"))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView1 = tab!!.customView
                val bind1 = TabItemBinding.bind(customView1!!)
                bind1.linear.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.tab_item_round)
                bind1.itemTv.setTextColor(Color.parseColor("#FFFFFF"))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        return binding.root
    }

    private fun loadCategory() {
        categoryList = ArrayList()
        categoryList.add(getString(R.string.day_packages))
        categoryList.add(getString(R.string.haftalik_packages))
        categoryList.add(getString(R.string.oylik_packages))
    }

    private fun setTabs() {
        for (i in 0 until binding.tab.tabCount) {
            val tab_item: TabItemBinding =
                TabItemBinding.inflate(LayoutInflater.from(requireContext()))
            tab_item.itemTv.text = categoryList[i]
            binding.tab.getTabAt(i)?.customView = tab_item.root
            if (i != 0) {
                tab_item.linear.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.tab_item_round)
                tab_item.itemTv.setTextColor(Color.parseColor("#FFFFFF"))

            } else {
                tab_item.linear.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.tab_item_round2)

                tab_item.itemTv.setTextColor(Color.parseColor("#01B4FF"))
            }


        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PackageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = param1
    }

}