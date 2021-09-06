package uz.juo.ussd.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.juo.ussd.MainActivity
import uz.juo.ussd.databinding.FragmentSplashBinding
import uz.juo.ussd.ui.language.LanguageActivity
import uz.juo.ussd.utils.SharedPreference

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
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
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        val handler = Handler(Looper.getMainLooper())
        val language = SharedPreference.getInstance(requireContext())
        handler.postDelayed({
            if (language.haslang) {
                SplashActivity().finish()
                var intent = Intent(requireContext(), MainActivity::class.java)
                activity?.finish()
                startActivity(intent)
            } else {
                var intent = Intent(requireContext(), LanguageActivity::class.java)
                activity?.finish()
                startActivity(intent)
            }
        }, 2000)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        findNavController().popBackStack()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}