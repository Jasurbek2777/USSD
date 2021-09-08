package uz.juo.ussd.ui.infoTarif

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
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import uz.juo.ussd.MainActivity
import uz.juo.ussd.databinding.FragmentInfoTarifBinding
import uz.juo.ussd.models.Tariflar

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class InfoTarifFragment : Fragment() {
    lateinit var binding: FragmentInfoTarifBinding
    private var param1: Tariflar? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Tariflar
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoTarifBinding.inflate(inflater, container, false)
        (activity as MainActivity).supportActionBar?.title = param1?.name
        binding.apply {
            name.text = param1?.name
            nameTv.text = param1?.name
            tv1.text = param1?.month_min
            tv2.text = param1?.mont_mb
            tv3.text = param1?.month_sms
            price.text = param1?.sum_mont
            desc.text = param1?.info
            var count = 0
            Connection.setOnClickListener {
                count++
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

        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoTarifFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}