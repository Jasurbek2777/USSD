package uz.juo.ussd.ui.service

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
import com.google.firebase.firestore.FirebaseFirestore
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import uz.juo.ussd.MainActivity
import uz.juo.ussd.R
import uz.juo.ussd.adapters.RvAdapterService
import uz.juo.ussd.databinding.FragmentServiceBinding
import uz.juo.ussd.models.Service
import uz.juo.ussd.models.Ussd
import uz.juo.ussd.utils.SharedPreference

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ServiceFragment : Fragment() {
    var db = FirebaseFirestore.getInstance()
    lateinit var list: ArrayList<Service>
    lateinit var binding: FragmentServiceBinding
    lateinit var adapter: RvAdapterService
    var count = 0
    private var param1: String? = null
    private var param2: String? = null

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
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.xizmat_label)
        when (SharedPreference.getInstance(requireContext()).lang) {
            "ru" -> {
                list= ArrayList()
                db.collection("ServiceRu").addSnapshotListener { value, error ->
                    value?.documents?.forEach {
                        var data = it.toObject(Service::class.java)
                        if (data != null) {
                            list.add(data)
                        }
                        if (list.size == value?.documents?.size) {
                            adapter = RvAdapterService(list, object : RvAdapterService.setOnCLick {
                                override fun itemOnClick(data: Service, position: Int) {
                                    openDialog(data.name, data.info1)
                                }
                            })
                            binding.rv.adapter=adapter
                        }
                    }
                }
            }
            "en" -> {
                list= ArrayList()
                db.collection("Service").addSnapshotListener { value, error ->
                    value?.documents?.forEach {
                        var data = it.toObject(Service::class.java)
                        if (data != null) {
                            list.add(data)
                        }
                        if (list.size == value?.documents?.size) {
                            adapter = RvAdapterService(list, object : RvAdapterService.setOnCLick {
                                override fun itemOnClick(data: Service, position: Int) {
                                    openDialog(data.name, data.info1)
                                }
                            })
                            binding.rv.adapter=adapter
                        }
                    }
                }
            }
            "uz" -> {
                list=ArrayList()
                db.collection("ServiceCril").addSnapshotListener { value, error ->
                    value?.documents?.forEach {
                        var data = it.toObject(Service::class.java)
                        if (data != null) {
                            list.add(data)
                        }
                        if (list.size == value?.documents?.size) {
                            adapter = RvAdapterService(list, object : RvAdapterService.setOnCLick {
                                override fun itemOnClick(data: Service, position: Int) {
                                    openDialog(data.name, data.info1)
                                }
                            })
                            binding.rv.adapter=adapter
                        }
                    }
                }
            }
        }
        return binding.root
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
                        count = 0
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
                            count = 0
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
            ServiceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}