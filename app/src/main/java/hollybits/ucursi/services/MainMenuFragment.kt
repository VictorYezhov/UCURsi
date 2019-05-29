package hollybits.ucursi.services

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import hollybits.ucursi.R
import kotlinx.android.synthetic.main.main_menu_fragment.*

class MainMenuFragment : Fragment() {

    companion object {
        fun newInstance() = MainMenuFragment()
    }

    interface MainMenuEvents{
        fun moveToAccount()
        fun moveToOrderDocument()
    }

    private lateinit var viewModel: ServicesMenuViewModel
    private var events:MainMenuEvents? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainMenuEvents) {
            events = context
        } else {
            throw RuntimeException("$context must implement LoginEvents interface in order to communicate with this fragment")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ServicesMenuViewModel::class.java)

        documentCardView.setOnClickListener {
            events!!.moveToOrderDocument()
        }

        accountImageView.setOnClickListener {
            events!!.moveToAccount()
        }
    }

    override fun onDetach() {
        super.onDetach()
        events = null
    }


}
