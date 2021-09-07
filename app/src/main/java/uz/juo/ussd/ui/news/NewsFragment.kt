package uz.juo.ussd.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import uz.juo.ussd.MainActivity
import uz.juo.ussd.R
import uz.juo.ussd.adapters.RvAdapter
import uz.juo.ussd.adapters.RvAdapterSms
import uz.juo.ussd.databinding.FragmentNewsBinding
import uz.juo.ussd.models.News
import uz.juo.ussd.models.SmsPaketlar
import uz.juo.ussd.utils.SharedPreference

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NewsFragment : Fragment() {
    lateinit var adapter: RvAdapter
    var db = FirebaseFirestore.getInstance()
    lateinit var binding: FragmentNewsBinding
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
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        (activity as MainActivity).supportActionBar?.title =getString(R.string.news)

            when (SharedPreference.getInstance(requireContext()).lang) {
                "ru" -> {
                    var list = ArrayList<News>()
                    db.collection("NewsRu").addSnapshotListener { value, error ->
                        value?.documentChanges?.forEach {
                            when (it.type) {
                                DocumentChange.Type.ADDED -> {
                                    val toObject = it.document.toObject(News::class.java)
                                    list.add(toObject)
                                    if (list.size == value.documentChanges.size) setData(list)
                                }
                            }
                        }


                    }

                }
                "uz" -> {

                    var list = ArrayList<News>()
                    db.collection("NewsCril").addSnapshotListener { value, error ->
                        value?.documentChanges?.forEach {
                            when (it.type) {
                                DocumentChange.Type.ADDED -> {
                                    val toObject = it.document.toObject(News::class.java)
                                    list.add(toObject)
                                    if (list.size == value.documentChanges.size) setData(list)
                                }
                            }
                        }


                    }
                }
                "en" -> {

                    var list = ArrayList<News>()
                    db.collection("News").addSnapshotListener { value, error ->
                        value?.documentChanges?.forEach {
                            when (it.type) {
                                DocumentChange.Type.ADDED -> {
                                    val toObject = it.document.toObject(News::class.java)
                                    list.add(toObject)
                                    if (list.size == value.documentChanges.size) setData(list)
                                }
                            }
                        }


                    }
                }
            }


        return binding.root
    }

    private fun setData(list: ArrayList<News>) {
        adapter = RvAdapter(list, object : RvAdapter.setOnCLick {
            override fun itemOnClick(data: News, position: Int) {

            }

            override fun itemActiveClick(
                data: News,
                position: Int
            ) {

            }
        })
        binding.rv.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}