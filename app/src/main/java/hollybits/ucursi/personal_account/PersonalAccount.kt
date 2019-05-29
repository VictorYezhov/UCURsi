package hollybits.ucursi.personal_account

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.Auth

import hollybits.ucursi.R
import hollybits.ucursi.main.AuthDataViewModel
import kotlinx.android.synthetic.main.fragment_test_data_shower.*
import kotlinx.android.synthetic.main.personal_account_fragment.*
import kotlinx.android.synthetic.main.personal_account_fragment.view.*

class PersonalAccount : Fragment() {

    companion object {
        fun newInstance() = PersonalAccount()
        private const val TAG = "PersonalAccount"
    }

    private lateinit var userDataViewModel: AccountViewModel
    private lateinit var authViewModel:AuthDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.personal_account_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userDataViewModel = ViewModelProviders.of(activity!!).get(AccountViewModel::class.java)
        authViewModel = ViewModelProviders.of(activity!!).get(AuthDataViewModel::class.java)
        userDataViewModel.loadAccountData(authViewModel.getAuthenticatedUser()?.email!!, "VUNVQXBwVmVyc2lvbjAuN3NhdWx0")
        setupDataListeners()

    }

    private fun setupDataListeners(){

        userDataViewModel.isDataLoaded.observe(this, Observer {
            loading_layout.visibility = View.GONE
        })
        userDataViewModel.userName.observe(this, Observer { userName->
            userNameUA.text = userName
        })

        userDataViewModel.userEmail.observe(this, Observer { email ->
            Log.i(TAG, "setting email: $email")
            userEmail.text = email
        })

        userDataViewModel.userPhone.observe(this, Observer { phone->
            userPhone.text = phone
        })
        userDataViewModel.usersGrop.observe(this, Observer { group->
            userGroup.text = group
        })
        userDataViewModel.userFatherName.observe(this, Observer { fatherName->
            userFatherName.text = fatherName
        })
    }

}
