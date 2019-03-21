package hollybits.ucursi.splash

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import hollybits.ucursi.R
import kotlinx.android.synthetic.main.splash_fragment.*
import java.lang.RuntimeException

class SplashFragment : Fragment() {

    interface SplashEvents{
        fun moveToLogin()
    }
    companion object {
        fun newInstance() = SplashFragment()
    }
    private var events: SplashEvents? = null

    private lateinit var viewModel: SplashViewModel
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is SplashEvents) {
            events = context
        }else{
            throw RuntimeException("$context must implement SplashEvents interface")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)

        imageView.postDelayed({
            events!!.moveToLogin()
        }, 2000)
    }

    override fun onDetach() {
        super.onDetach()
        events = null
    }
}
