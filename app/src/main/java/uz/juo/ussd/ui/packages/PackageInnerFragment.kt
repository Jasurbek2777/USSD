package uz.juo.ussd.ui.packages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import uz.juo.ussd.MainActivity
import uz.juo.ussd.R
import uz.juo.ussd.adapters.RvAdapterInternet
import uz.juo.ussd.adapters.RvAdapterMinut
import uz.juo.ussd.adapters.RvAdapterSms
import uz.juo.ussd.databinding.FragmentPackageInnerBinding
import uz.juo.ussd.models.Internetpaketlar
import uz.juo.ussd.models.MinutPackage
import uz.juo.ussd.models.SmsPaketlar
import uz.juo.ussd.utils.SharedPreference

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PackageInnerFragment : Fragment() {
    lateinit var binding: FragmentPackageInnerBinding
    private var param1: String? = null
    var db = FirebaseFirestore.getInstance()
    private var param2: String? = null
    lateinit var daqiqaAdapter: RvAdapterMinut
    lateinit var internetAdapter: RvAdapterInternet
    lateinit var smsAdapter: RvAdapterSms
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPackageInnerBinding.inflate(inflater, container, false)
        loadData(param1!!)
        return binding.root
    }

    private fun loadData(param1: String) {
        when (SharedPreference.getInstance(requireContext()).lang) {
            "ru" -> {
                when (param1) {
                    "Minut" -> {
                        (activity as MainActivity).supportActionBar?.title =
                            getString(R.string.minute_label)
                        when (param2?.toInt()) {
                            0 -> {
                                var list = ArrayList<MinutPackage>()
                                db.collection("MinutesRu").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(MinutPackage::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    daqiqaAdapter =
                                        RvAdapterMinut(list, object : RvAdapterMinut.setOnCLick {
                                            override fun itemOnClick(
                                                data: MinutPackage,
                                                position: Int
                                            ) {

                                            }
                                        })
                                    binding.rv.adapter = daqiqaAdapter

                                }
                            }
                            1 -> {
                                var list = ArrayList<MinutPackage>()
                                db.collection("ReplacementRu").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(MinutPackage::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    daqiqaAdapter =
                                        RvAdapterMinut(list, object : RvAdapterMinut.setOnCLick {
                                            override fun itemOnClick(
                                                data: MinutPackage,
                                                position: Int
                                            ) {

                                            }
                                        })
                                    binding.rv.adapter = daqiqaAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<MinutPackage>()
                                db.collection("ConstructorRu").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(MinutPackage::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    daqiqaAdapter =
                                        RvAdapterMinut(list, object : RvAdapterMinut.setOnCLick {
                                            override fun itemOnClick(
                                                data: MinutPackage,
                                                position: Int
                                            ) {

                                            }
                                        })
                                    binding.rv.adapter = daqiqaAdapter

                                }
                            }
                        }


                    }
                    "Internet" -> {
                        (activity as MainActivity).supportActionBar?.title =
                            getString(R.string.internet_label)
                        when (param2?.toInt()) {
                            0 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("DayInternetRu").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(list, object : RvAdapterInternet.setOnCLick {
                                        override fun itemOnClick(data: Internetpaketlar, position: Int) {

                                        }
                                    })
                                    binding.rv.adapter = internetAdapter

                                }

                            }
                            1 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("InternetMonthRu").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(list, object : RvAdapterInternet.setOnCLick {
                                        override fun itemOnClick(data: Internetpaketlar, position: Int) {

                                        }
                                    })
                                    binding.rv.adapter = internetAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("NightInternetRu").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(list, object : RvAdapterInternet.setOnCLick {
                                        override fun itemOnClick(data: Internetpaketlar, position: Int) {

                                        }
                                    })
                                    binding.rv.adapter = internetAdapter

                                }
                            }
                        }
                    }
                    "SMS" -> {
                        (activity as MainActivity).supportActionBar?.title =
                            getString(R.string.internet_label)
                        when (param2?.toInt()) {
                            0 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("SMSDayRu").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter = RvAdapterSms(list, object : RvAdapterSms.setOnCLick {
                                        override fun itemOnClick(data: SmsPaketlar, position: Int) {

                                        }

                                        override fun itemActiveClick(
                                            data: SmsPaketlar,
                                            position: Int
                                        ) {

                                        }
                                    })
                                    binding.rv.adapter = smsAdapter

                                }

                            }
                            1 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("SMSMonthRu").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter = RvAdapterSms(list, object : RvAdapterSms.setOnCLick {
                                        override fun itemOnClick(data: SmsPaketlar, position: Int) {

                                        }

                                        override fun itemActiveClick(
                                            data: SmsPaketlar,
                                            position: Int
                                        ) {

                                        }
                                    })
                                    binding.rv.adapter = smsAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("Non-StopRu").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter = RvAdapterSms(list, object : RvAdapterSms.setOnCLick {
                                        override fun itemOnClick(data: SmsPaketlar, position: Int) {

                                        }

                                        override fun itemActiveClick(
                                            data: SmsPaketlar,
                                            position: Int
                                        ) {

                                        }
                                    })
                                    binding.rv.adapter = smsAdapter

                                }
                            }
                        }
                    }
                }
            }
            "uz" -> {
                when (param1) {
                    "Minut" -> {
                        (activity as MainActivity).supportActionBar?.title =
                            getString(R.string.minute_label)
                        when (param2?.toInt()) {
                            0 -> {
                                var list = ArrayList<MinutPackage>()
                                db.collection("MinutesCril").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(MinutPackage::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    daqiqaAdapter =
                                        RvAdapterMinut(list, object : RvAdapterMinut.setOnCLick {
                                            override fun itemOnClick(
                                                data: MinutPackage,
                                                position: Int
                                            ) {

                                            }
                                        })
                                    binding.rv.adapter = daqiqaAdapter

                                }
                            }
                            1 -> {
                                var list = ArrayList<MinutPackage>()
                                db.collection("ReplacementCril").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(MinutPackage::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    daqiqaAdapter =
                                        RvAdapterMinut(list, object : RvAdapterMinut.setOnCLick {
                                            override fun itemOnClick(
                                                data: MinutPackage,
                                                position: Int
                                            ) {

                                            }
                                        })
                                    binding.rv.adapter = daqiqaAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<MinutPackage>()
                                db.collection("ConstructorCril").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(MinutPackage::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    daqiqaAdapter =
                                        RvAdapterMinut(list, object : RvAdapterMinut.setOnCLick {
                                            override fun itemOnClick(
                                                data: MinutPackage,
                                                position: Int
                                            ) {

                                            }
                                        })
                                    binding.rv.adapter = daqiqaAdapter

                                }
                            }
                        }


                    }
                    "Internet" -> {
                        (activity as MainActivity).supportActionBar?.title =
                            getString(R.string.internet_label)
                        when (param2?.toInt()) {
                            0 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("DayInternetCril").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(list, object : RvAdapterInternet.setOnCLick {
                                        override fun itemOnClick(data: Internetpaketlar, position: Int) {

                                        }
                                    })
                                    binding.rv.adapter = internetAdapter

                                }

                            }
                            1 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("InternetMonthCril").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(list, object : RvAdapterInternet.setOnCLick {
                                        override fun itemOnClick(data: Internetpaketlar, position: Int) {

                                        }
                                    })
                                    binding.rv.adapter = internetAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("NightInternetCril").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(list, object : RvAdapterInternet.setOnCLick {
                                        override fun itemOnClick(data: Internetpaketlar, position: Int) {

                                        }
                                    })
                                    binding.rv.adapter = internetAdapter

                                }
                            }
                        }
                    }
                    "SMS" -> {
                        (activity as MainActivity).supportActionBar?.title =
                            getString(R.string.sms_label)
                        when (param2?.toInt()) {
                            0 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("SMSDayCril").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter = RvAdapterSms(list, object : RvAdapterSms.setOnCLick {
                                        override fun itemOnClick(data: SmsPaketlar, position: Int) {

                                        }

                                        override fun itemActiveClick(
                                            data: SmsPaketlar,
                                            position: Int
                                        ) {

                                        }
                                    })
                                    binding.rv.adapter = smsAdapter

                                }

                            }
                            1 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("SMSMonthCril").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter = RvAdapterSms(list, object : RvAdapterSms.setOnCLick {
                                        override fun itemOnClick(data: SmsPaketlar, position: Int) {

                                        }

                                        override fun itemActiveClick(
                                            data: SmsPaketlar,
                                            position: Int
                                        ) {

                                        }
                                    })
                                    binding.rv.adapter = smsAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("Non-StopCril").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter = RvAdapterSms(list, object : RvAdapterSms.setOnCLick {
                                        override fun itemOnClick(data: SmsPaketlar, position: Int) {

                                        }

                                        override fun itemActiveClick(
                                            data: SmsPaketlar,
                                            position: Int
                                        ) {

                                        }
                                    })
                                    binding.rv.adapter = smsAdapter

                                }
                            }
                        }
                    }
                }
            }
            "en" -> {
                when (param1) {
                    "Minut" -> {
                        (activity as MainActivity).supportActionBar?.title =
                            getString(R.string.minute_label)
                        when (param2?.toInt()) {
                            0 -> {
                                var list = ArrayList<MinutPackage>()
                                db.collection("Minutes").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(MinutPackage::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    daqiqaAdapter =
                                        RvAdapterMinut(list, object : RvAdapterMinut.setOnCLick {
                                            override fun itemOnClick(
                                                data: MinutPackage,
                                                position: Int
                                            ) {

                                            }
                                        })
                                    binding.rv.adapter = daqiqaAdapter

                                }
                            }
                            1 -> {
                                var list = ArrayList<MinutPackage>()
                                db.collection("Replacement").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(MinutPackage::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    daqiqaAdapter =
                                        RvAdapterMinut(list, object : RvAdapterMinut.setOnCLick {
                                            override fun itemOnClick(
                                                data: MinutPackage,
                                                position: Int
                                            ) {

                                            }
                                        })
                                    binding.rv.adapter = daqiqaAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<MinutPackage>()
                                db.collection("Constructor").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(MinutPackage::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    daqiqaAdapter =
                                        RvAdapterMinut(list, object : RvAdapterMinut.setOnCLick {
                                            override fun itemOnClick(
                                                data: MinutPackage,
                                                position: Int
                                            ) {

                                            }
                                        })
                                    binding.rv.adapter = daqiqaAdapter

                                }
                            }
                        }


                    }
                    "Internet" -> {
                        (activity as MainActivity).supportActionBar?.title =
                            getString(R.string.internet_label)
                        when (param2?.toInt()) {
                            0 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("DayInternet").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(list, object : RvAdapterInternet.setOnCLick {
                                        override fun itemOnClick(data: Internetpaketlar, position: Int) {

                                        }
                                    })
                                    binding.rv.adapter = internetAdapter

                                }

                            }
                            1 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("InternetMonth").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(list, object : RvAdapterInternet.setOnCLick {
                                        override fun itemOnClick(data: Internetpaketlar, position: Int) {

                                        }
                                    })
                                    binding.rv.adapter = internetAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("NightInternet").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(list, object : RvAdapterInternet.setOnCLick {
                                        override fun itemOnClick(data: Internetpaketlar, position: Int) {

                                        }
                                    })
                                    binding.rv.adapter = internetAdapter

                                }
                            }
                        }
                    }
                    "SMS" -> {
                        (activity as MainActivity).supportActionBar?.title =
                            getString(R.string.sms_label)
                        when (param2?.toInt()) {
                            0 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("SMSDay").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter = RvAdapterSms(list, object : RvAdapterSms.setOnCLick {
                                        override fun itemOnClick(data: SmsPaketlar, position: Int) {

                                        }

                                        override fun itemActiveClick(
                                            data: SmsPaketlar,
                                            position: Int
                                        ) {

                                        }
                                    })
                                    binding.rv.adapter = smsAdapter

                                }

                            }
                            1 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("SMSMonth").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter = RvAdapterSms(list, object : RvAdapterSms.setOnCLick {
                                        override fun itemOnClick(data: SmsPaketlar, position: Int) {

                                        }

                                        override fun itemActiveClick(
                                            data: SmsPaketlar,
                                            position: Int
                                        ) {

                                        }
                                    })
                                    binding.rv.adapter = smsAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("Non-Stop").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject = it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter = RvAdapterSms(list, object : RvAdapterSms.setOnCLick {
                                        override fun itemOnClick(data: SmsPaketlar, position: Int) {

                                        }

                                        override fun itemActiveClick(
                                            data: SmsPaketlar,
                                            position: Int
                                        ) {

                                        }
                                    })
                                    binding.rv.adapter = smsAdapter

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PackageInnerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}