package uz.juo.ussd.ui.packages

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
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
        viewpagerAdapter = ViewPager2Adapter(param1.toString(), requireActivity())
        loadCategory()
        binding.viewpager.adapter = viewpagerAdapter
        TabLayoutMediator(binding.tab, binding.viewpager) { tab, i ->
            tab.text = categoryList[i]
        }.attach()
        setTabs()
        var count=0
        binding.checkTv.setOnClickListener{
                Dexter.withContext(context)
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
                                    context,
                                    "Please grant the permission !",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "You did not grant the permission please manually grand it!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                val uri = Uri.fromParts("package", activity?.packageName, null)
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
        when(param1){
            "Internet"->{
                categoryList.add(getString(R.string.day_packages))
                categoryList.add(getString(R.string.oylik_packages))
                categoryList.add(getString(R.string.night_pacages))
            }
            "Minut"->{
                categoryList.add(getString(R.string.minut_package))
                categoryList.add(getString(R.string.foydali_almashuv))
                categoryList.add(getString(R.string.construktor_abonent))
            }
            "SMS"->{
                categoryList.add(getString(R.string.day_packages))
                categoryList.add(getString(R.string.oylik_packages))
                categoryList.add(getString(R.string.xalqaro_paketlar))
            }

        }

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