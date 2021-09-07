package uz.juo.ussd.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import uz.juo.ussd.MainActivity
import uz.juo.ussd.R
import uz.juo.ussd.adapters.RvAdapterTarif
import uz.juo.ussd.adapters.ViewPagerAdapter
import uz.juo.ussd.databinding.FragmentHomeBinding
import uz.juo.ussd.models.Tariflar
import uz.juo.ussd.utils.FlipVerticalPageTransformer
import uz.juo.ussd.utils.SharedPreference
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    lateinit var list: ArrayList<Tariflar>
    private var _binding: FragmentHomeBinding? = null
    lateinit var adapter: RvAdapterTarif
    var db = FirebaseFirestore.getInstance()
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
        return root
    }

    private fun loadImages() {
        when (SharedPreference.getInstance(context).lang) {
            "ru" -> {
                list = ArrayList()
                db.collection("DefinitionsRu").addSnapshotListener { value, error ->
                    value?.documentChanges?.forEach {
                        var data = it.document.toObject(Tariflar::class.java)
                        list.add(data)
                        if (list.size == value.documentChanges.size) {
                           setViewPagerData()
                        }

                    }
                }

            }
            "en" -> {
                list = ArrayList()
                db.collection("Definitions").addSnapshotListener { value, _ ->
                    value?.documentChanges?.forEach {
                        var data = it.document.toObject(Tariflar::class.java)
                        list.add(data)
                        if (list.size == value.documentChanges.size) {
                           setViewPagerData()
                        }
                    }
                }

            }
            "uz" -> {
                list = ArrayList()
                db.collection("DefinitionsCril").addSnapshotListener { value, error ->
                    value?.documentChanges?.forEach {
                        var data = it.document.toObject(Tariflar::class.java)
                        list.add(data)
                        if (list.size == value.documentChanges.size) {
                           setViewPagerData()
                        }
                    }
                }
            }

        }
    }

    private fun setViewPagerData() {
        val wormDotsIndicator = _binding!!.springDotsIndicator
        val viewPager = _binding!!.viewPager
        var viewPagerAdapter = ViewPagerAdapter(list)
        binding.viewPager.adapter = viewPagerAdapter
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
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 100, 2500)
        binding.viewPager.setPageTransformer(true, FlipVerticalPageTransformer())
        wormDotsIndicator.setViewPager(_binding!!.viewPager)
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