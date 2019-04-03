package hollybits.ucursi.login

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import hollybits.ucursi.R
import kotlinx.android.synthetic.main.login_fragment.*
import java.lang.RuntimeException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import android.content.Intent

import android.util.Log
import hollybits.ucursi.common.ErrorObserver


class LoginFragment : Fragment(), ErrorObserver.ErrorDisplayer {

    companion object {
        private const val TAG = "LoginFragment"
        private const val GOOGLE_SG_CODE = 565
        fun newInstance() = LoginFragment()
    }
    interface LoginEvents{
        fun userIsAlreadyLoggedIn()
    }

    private lateinit var viewModel: LoginViewModel
    private var eventsHandler: LoginEvents? = null
    private lateinit var googleSignInClient:GoogleSignInClient

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is LoginEvents){
            eventsHandler = context
        }else{
            throw RuntimeException("$context must implement LoginEvents interface in order to communicate with this fragment")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient =  GoogleSignIn.getClient(activity!!, gso)
        viewModel.initGoogleClient(googleSignInClient)
        viewModel.checkUser()
        setupDataListeners()
        setupListeners()

    }

    private fun setupDataListeners(){
        viewModel.isUserLoginned().observe(this, Observer {
            if(it){
                eventsHandler?.userIsAlreadyLoggedIn()
            }
        })

        viewModel.errorState().observe(this, ErrorObserver(this))
    }
    private fun setupListeners(){
        login_button.setOnClickListener {
            startActivityForResult(googleSignInClient.signInIntent, GOOGLE_SG_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == GOOGLE_SG_CODE) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            viewModel.handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(data))
        }
    }

    override fun onDetach() {
        super.onDetach()
        eventsHandler = null
    }

    override fun displayError(reason: String) {
        Log.i(TAG, "Error: $reason")
        //TODO Alert dialog
    }
}
