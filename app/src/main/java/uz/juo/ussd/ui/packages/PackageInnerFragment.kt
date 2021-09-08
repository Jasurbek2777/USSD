package uz.juo.ussd.ui.packages

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
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
    var count = 0
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
                                                openDialog(
                                                    data.name,
                                                    data.code + "\n" + data.summ + "\n" + data.day_month
                                                )
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
                                                openDialog(
                                                    data.name,
                                                    data.code + "\n" + data.summ + "\n" + data.day_month
                                                )
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
                                                openDialog(
                                                    data.name,
                                                    data.code + "\n" + data.summ + "\n" + data.day_month
                                                )
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
                                                val toObject =
                                                    it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(
                                        list,
                                        object : RvAdapterInternet.setOnCLick {
                                            override fun itemOnClick(
                                                data: Internetpaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.gb,
                                                    data.activeCode + "\n" + data.name_price + data.price + "\n" + data.day_text + data.day_count
                                                )
                                            }
                                        })
                                    binding.rv.adapter = internetAdapter

                                }

                            }
                            1 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("InternetMonthRu")
                                    .addSnapshotListener { value, error ->
                                        value?.documentChanges?.forEach {
                                            when (it.type) {
                                                DocumentChange.Type.ADDED -> {
                                                    val toObject =
                                                        it.document.toObject(Internetpaketlar::class.java)
                                                    list.add(toObject)
                                                }
                                            }
                                        }
                                        internetAdapter = RvAdapterInternet(
                                            list,
                                            object : RvAdapterInternet.setOnCLick {
                                                override fun itemOnClick(
                                                    data: Internetpaketlar,
                                                    position: Int
                                                ) {

                                                }
                                            })
                                        binding.rv.adapter = internetAdapter

                                    }
                            }
                            2 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("NightInternetRu")
                                    .addSnapshotListener { value, error ->
                                        value?.documentChanges?.forEach {
                                            when (it.type) {
                                                DocumentChange.Type.ADDED -> {
                                                    val toObject =
                                                        it.document.toObject(Internetpaketlar::class.java)
                                                    list.add(toObject)
                                                }
                                            }
                                        }
                                        internetAdapter = RvAdapterInternet(
                                            list,
                                            object : RvAdapterInternet.setOnCLick {
                                                override fun itemOnClick(
                                                    data: Internetpaketlar,
                                                    position: Int
                                                ) {

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
                                                val toObject =
                                                    it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter =
                                        RvAdapterSms(list, object : RvAdapterSms.setOnCLick {

                                            override fun itemActiveClick(
                                                data: SmsPaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.summ_user,
                                                    data.activeCode + "\n" + data.price + "\n" + data.day_sms
                                                )
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
                                                val toObject =
                                                    it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter =
                                        RvAdapterSms(list, object : RvAdapterSms.setOnCLick {

                                            override fun itemActiveClick(
                                                data: SmsPaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.summ_user,
                                                    data.activeCode + "\n" + data.price + "\n" + data.day_sms
                                                )
                                            }
                                        })
                                    binding.rv.adapter = smsAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("SMSCountryRu").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter =
                                        RvAdapterSms(list, object : RvAdapterSms.setOnCLick {

                                            override fun itemActiveClick(
                                                data: SmsPaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.summ_user,
                                                    data.activeCode + "\n" + data.price + "\n" + data.day_sms
                                                )
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
                                                openDialog(
                                                    data.name,
                                                    data.code + "\n" + data.summ + "\n" + data.day_month
                                                )
                                            }
                                        })
                                    binding.rv.adapter = daqiqaAdapter

                                }
                            }
                            1 -> {
                                var list = ArrayList<MinutPackage>()
                                db.collection("ReplacementCril")
                                    .addSnapshotListener { value, error ->
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
                                            RvAdapterMinut(
                                                list,
                                                object : RvAdapterMinut.setOnCLick {
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
                                db.collection("ConstructorCril")
                                    .addSnapshotListener { value, error ->
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
                                            RvAdapterMinut(
                                                list,
                                                object : RvAdapterMinut.setOnCLick {
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
                                db.collection("DayInternetCril")
                                    .addSnapshotListener { value, error ->
                                        value?.documentChanges?.forEach {
                                            when (it.type) {
                                                DocumentChange.Type.ADDED -> {
                                                    val toObject =
                                                        it.document.toObject(Internetpaketlar::class.java)
                                                    list.add(toObject)
                                                }
                                            }
                                        }
                                        internetAdapter = RvAdapterInternet(
                                            list,
                                            object : RvAdapterInternet.setOnCLick {
                                                override fun itemOnClick(
                                                    data: Internetpaketlar,
                                                    position: Int
                                                ) {

                                                }
                                            })
                                        binding.rv.adapter = internetAdapter

                                    }

                            }
                            1 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("InternetMonthCril")
                                    .addSnapshotListener { value, error ->
                                        value?.documentChanges?.forEach {
                                            when (it.type) {
                                                DocumentChange.Type.ADDED -> {
                                                    val toObject =
                                                        it.document.toObject(Internetpaketlar::class.java)
                                                    list.add(toObject)
                                                }
                                            }
                                        }
                                        internetAdapter = RvAdapterInternet(
                                            list,
                                            object : RvAdapterInternet.setOnCLick {
                                                override fun itemOnClick(
                                                    data: Internetpaketlar,
                                                    position: Int
                                                ) {

                                                }
                                            })
                                        binding.rv.adapter = internetAdapter

                                    }
                            }
                            2 -> {
                                var list = ArrayList<Internetpaketlar>()
                                db.collection("NightInternetCril")
                                    .addSnapshotListener { value, error ->
                                        value?.documentChanges?.forEach {
                                            when (it.type) {
                                                DocumentChange.Type.ADDED -> {
                                                    val toObject =
                                                        it.document.toObject(Internetpaketlar::class.java)
                                                    list.add(toObject)
                                                }
                                            }
                                        }
                                        internetAdapter = RvAdapterInternet(
                                            list,
                                            object : RvAdapterInternet.setOnCLick {
                                                override fun itemOnClick(
                                                    data: Internetpaketlar,
                                                    position: Int
                                                ) {

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
                                                val toObject =
                                                    it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter =
                                        RvAdapterSms(list, object : RvAdapterSms.setOnCLick {

                                            override fun itemActiveClick(
                                                data: SmsPaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.summ_user,
                                                    data.activeCode + "\n" + data.price + "\n" + data.day_sms
                                                )
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
                                                val toObject =
                                                    it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter =
                                        RvAdapterSms(list, object : RvAdapterSms.setOnCLick {

                                            override fun itemActiveClick(
                                                data: SmsPaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.summ_user,
                                                    data.activeCode + "\n" + data.price + "\n" + data.day_sms
                                                )
                                            }
                                        })
                                    binding.rv.adapter = smsAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("SMSCountryCril").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter =
                                        RvAdapterSms(list, object : RvAdapterSms.setOnCLick {

                                            override fun itemActiveClick(
                                                data: SmsPaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.summ_user,
                                                    data.activeCode + "\n" + data.price + "\n" + data.day_sms
                                                )
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
                                                openDialog(
                                                    data.name,
                                                    data.code + "\n" + data.summ + "\n" + data.day_month
                                                )
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
                                                openDialog(
                                                    data.name,
                                                    data.code + "\n" + data.summ + "\n" + data.day_month
                                                )
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
                                                openDialog(
                                                    data.name,
                                                    data.code + "\n" + data.summ + "\n" + data.day_month
                                                )
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
                                                val toObject =
                                                    it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(
                                        list,
                                        object : RvAdapterInternet.setOnCLick {
                                            override fun itemOnClick(
                                                data: Internetpaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.gb,
                                                    data.activeCode + "\n" + data.name_price + data.price + "\n" + data.day_text + data.day_count
                                                )
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
                                                val toObject =
                                                    it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(
                                        list,
                                        object : RvAdapterInternet.setOnCLick {
                                            override fun itemOnClick(
                                                data: Internetpaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.gb,
                                                    data.activeCode + "\n" + data.name_price + data.price + "\n" + data.day_text + data.day_count
                                                )
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
                                                val toObject =
                                                    it.document.toObject(Internetpaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    internetAdapter = RvAdapterInternet(
                                        list,
                                        object : RvAdapterInternet.setOnCLick {
                                            override fun itemOnClick(
                                                data: Internetpaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.gb,
                                                    data.activeCode + "\n" + data.name_price + data.price + "\n" + data.day_text + data.day_count
                                                )
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
                                                val toObject =
                                                    it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter =
                                        RvAdapterSms(list, object : RvAdapterSms.setOnCLick {

                                            override fun itemActiveClick(
                                                data: SmsPaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.summ_user,
                                                    data.activeCode + "\n" + data.price + "\n" + data.day_sms
                                                )
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
                                                val toObject =
                                                    it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter =
                                        RvAdapterSms(list, object : RvAdapterSms.setOnCLick {

                                            override fun itemActiveClick(
                                                data: SmsPaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.summ_user,
                                                    data.activeCode + "\n" + data.price + "\n" + data.day_sms
                                                )
                                            }
                                        })
                                    binding.rv.adapter = smsAdapter

                                }
                            }
                            2 -> {
                                var list = ArrayList<SmsPaketlar>()
                                db.collection("SMSCountry").addSnapshotListener { value, error ->
                                    value?.documentChanges?.forEach {
                                        when (it.type) {
                                            DocumentChange.Type.ADDED -> {
                                                val toObject =
                                                    it.document.toObject(SmsPaketlar::class.java)
                                                list.add(toObject)
                                            }
                                        }
                                    }
                                    smsAdapter =
                                        RvAdapterSms(list, object : RvAdapterSms.setOnCLick {

                                            override fun itemActiveClick(
                                                data: SmsPaketlar,
                                                position: Int
                                            ) {
                                                openDialog(
                                                    data.summ_user,
                                                    data.activeCode + "\n" + data.price + "\n" + data.day_sms
                                                )
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

    fun openDialog(name: String, info: String) {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(name)
            .setMessage(info)
        builder.setPositiveButton(
            getString(R.string.activlashtirish)
        ) { dialog, which ->

            Dexter.withContext(context)
                .withPermission(Manifest.permission.CALL_PHONE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        val callIntent = Intent(Intent.ACTION_CALL)
                        var hash = Uri.encode("#")
                        count=0
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
                            count=0
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
        builder.show()
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