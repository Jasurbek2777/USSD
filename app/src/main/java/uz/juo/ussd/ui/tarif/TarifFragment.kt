package uz.juo.ussd.ui.tarif

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import uz.juo.ussd.MainActivity
import uz.juo.ussd.R
import uz.juo.ussd.adapters.RvAdapterTarif
import uz.juo.ussd.databinding.FragmentTarifBinding
import uz.juo.ussd.models.Tariflar
import uz.juo.ussd.utils.Functions
import uz.juo.ussd.utils.SharedPreference

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TarifFragment : Fragment() {
    var db = FirebaseFirestore.getInstance()
    lateinit var binding: FragmentTarifBinding
    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter: RvAdapterTarif
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
        binding = FragmentTarifBinding.inflate(inflater, container, false)
        var list = ArrayList<Tariflar>()
        (activity as MainActivity).supportActionBar?.title = getString(R.string.tarif_label)
        when (SharedPreference.getInstance(context).lang) {
            "ru" -> {
                list = ArrayList()
                db.collection("DefinitionsRu").addSnapshotListener { value, error ->
                    value?.documentChanges?.forEach {
                        var data = it.document.toObject(Tariflar::class.java)
                        list.add(data)
                        if (value.documentChanges.size == list.size) {
                            adapter = RvAdapterTarif(list, object : RvAdapterTarif.setOnCLick {
                                override fun itemOnClick(data: Tariflar, position: Int) {

                                }

                                override fun moreItemClick(data: Tariflar, position: Int) {
                                    var b=Bundle()
                                    b.putSerializable("param1",data)
                                    findNavController().navigate(R.id.infoTarifFragment,b)

                                }
                            })
                            binding.rv.adapter = adapter
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
                        if (value?.documentChanges?.size == list.size) {
                            adapter = RvAdapterTarif(list, object : RvAdapterTarif.setOnCLick {
                                override fun itemOnClick(data: Tariflar, position: Int) {
                                }

                                override fun moreItemClick(data: Tariflar, position: Int) {
                                    var b=Bundle()
                                    b.putSerializable("param1",data)
                                    findNavController().navigate(R.id.infoTarifFragment,b)

                                }
                            })
                            binding.rv.adapter = adapter
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
                        if (value.documentChanges.size == list.size) {
                            adapter = RvAdapterTarif(list, object : RvAdapterTarif.setOnCLick {
                                override fun itemOnClick(data: Tariflar, position: Int) {

                                }

                                override fun moreItemClick(data: Tariflar, position: Int) {
                                    var b=Bundle()
                                    b.putSerializable("param1",data)
                                    findNavController().navigate(R.id.infoTarifFragment,b) }
                            })
                            binding.rv.adapter = adapter
                        }
                    }
                }
            }
        }
        var count = 0
        binding.checkTarif.setOnClickListener {
            Dexter.withContext(requireContext())
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
                                requireContext(),
                                "Please grant the permission !",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "You did not grant the permission please manually grand it!",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts(
                                "package",
                                (activity as MainActivity).packageName,
                                null
                            )
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
        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TarifFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}