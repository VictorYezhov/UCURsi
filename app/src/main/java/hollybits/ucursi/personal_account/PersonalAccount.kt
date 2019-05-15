package hollybits.ucursi.personal_account

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import hollybits.ucursi.R
import kotlinx.android.synthetic.main.fragment_test_data_shower.*
import kotlinx.android.synthetic.main.personal_account_fragment.*
import kotlinx.android.synthetic.main.personal_account_fragment.view.*

class PersonalAccount : Fragment() {

    companion object {
        fun newInstance() = PersonalAccount()
    }

    private lateinit var userDataViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.personal_account_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userDataViewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
        userDataViewModel.loadAccountData("zavaliy@ucu.edu.ua", "VUNVQXBwVmVyc2lvbjAuN3NhdWx0")
        setupDataListeners()

    }

    private fun setupDataListeners(){

        userDataViewModel.userName.observe(this, Observer { userName->
            userNameUA.text = userName
        })

        userDataViewModel.userEmail.observe(this, Observer { email ->
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
