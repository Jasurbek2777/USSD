package uz.juo.ussd.ui.tarif

import android.content.ContentValues
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
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
                        if(value.documentChanges.size==list.size){
                            adapter = RvAdapterTarif(list, object : RvAdapterTarif.setOnCLick {
                                override fun itemOnClick(data: Tariflar, position: Int) {
                                    Toast.makeText(requireContext(), data.code, Toast.LENGTH_SHORT).show()
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
                        if (value?.documentChanges?.size==list.size){
                            adapter = RvAdapterTarif(list, object : RvAdapterTarif.setOnCLick {
                                override fun itemOnClick(data: Tariflar, position: Int) {
                                    Toast.makeText(requireContext(), data.code, Toast.LENGTH_SHORT).show()
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
                    }
                }
                adapter = RvAdapterTarif(list, object : RvAdapterTarif.setOnCLick {
                    override fun itemOnClick(data: Tariflar, position: Int) {
                        Toast.makeText(requireContext(), data.code, Toast.LENGTH_SHORT).show()
                    }
                })
                binding.rv.adapter = adapter
            }
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