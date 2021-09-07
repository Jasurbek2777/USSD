package uz.juo.ussd.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.juo.ussd.MainActivity
import uz.juo.ussd.R
import uz.juo.ussd.adapters.ViewPagerAdapter
import uz.juo.ussd.databinding.FragmentHomeBinding
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    lateinit var imageList: ArrayList<Int>
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        loadImages()
        (activity as MainActivity).supportActionBar?.show()
        (activity as MainActivity).supportActionBar?.title = ""
        val wormDotsIndicator = _binding!!.springDotsIndicator
        val viewPager = _binding!!.viewPager
        var viewPagerAdapter = ViewPagerAdapter(imageList)
        viewPager.adapter = viewPagerAdapter
        val handler = Handler(Looper.getMainLooper())
        binding.apply {
            ussd.setOnClickListener {
                var bundle = bundleOf(Pair("param1", "USSD"))
                findNavController().navigate(R.id.serviceFragment, bundle)
            }
            tarif.setOnClickListener {
                findNavController().navigate(R.id.tarifFragment)
            }
            xizmat.setOnClickListener {

                var bundle = bundleOf(Pair("param1", "Xizmat"))
                findNavController().navigate(R.id.serviceFragment, bundle)
            }
            minute.setOnClickListener {

                var bundle = bundleOf(Pair("param1", "Minut"))
                findNavController().navigate(R.id.packageFragment, bundle)
            }
            internet.setOnClickListener {

                var bundle = bundleOf(Pair("param1", "Internet"))
                findNavController().navigate(R.id.packageFragment, bundle)
            }
            sms.setOnClickListener {
                var bundle = bundleOf(Pair("param1", "SMS"))
                findNavController().navigate(R.id.packageFragment, bundle)
            }
        }
        val update = Runnable {
            if (viewPager.currentItem == 4) {
                viewPager.currentItem = 0
            } else {
                viewPager.currentItem++
            }
//            val anim = AnimationUtils.loadAnimation(
//                requireContext(), R.anim.anim
//            )
//            viewPager.startAnimation(anim)
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 100, 2500)
        wormDotsIndicator.setViewPager(_binding!!.viewPager)
        return root
    }

    private fun loadImages() {
        imageList = ArrayList()
        imageList.add(R.drawable.ussd_image1)
        imageList.add(R.drawable.ussd_image2)
        imageList.add(R.drawable.ussd_image3)
        imageList.add(R.drawable.ussd_image4)
        imageList.add(R.drawable.ussd_image5)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.show()
        (activity as MainActivity).supportActionBar?.title = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send -> {
                val uri =
                    Uri.parse("https://t.me/ussduz")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                }
            }
            R.id.share -> {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "http://play.google.com/store/apps/details?id=uz.pdp.uzmobile"
                )
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
    }

}