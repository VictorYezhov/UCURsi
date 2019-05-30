package hollybits.ucursi.printer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import hollybits.ucursi.R
import hollybits.ucursi.main.AuthDataViewModel
import hollybits.ucursi.personal_account.AccountViewModel
import hollybits.ucursi.personal_account.data.UserInfo
import kotlinx.android.synthetic.main.fragment_printer_info.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class PrinterInfoFragment : Fragment() {


    private lateinit var userDataViewModel: AccountViewModel
    private lateinit var authViewModel:AuthDataViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_printer_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userDataViewModel = ViewModelProviders.of(activity!!).get(AccountViewModel::class.java)
        authViewModel = ViewModelProviders.of(activity!!).get(AuthDataViewModel::class.java)
        //userDataViewModel.loadAccountData(authViewModel.getAuthenticatedUser()?.email!!, "VUNVQXBwVmVyc2lvbjAuN3NhdWx0")

        val info = userDataViewModel.getUserInfo()
        if(info == null){
            userDataViewModel.loadAccountData(authViewModel.getAuthenticatedUser()?.email!!, "VUNVQXBwVmVyc2lvbjAuN3NhdWx0")
            userDataViewModel.isDataLoaded.observe(this, Observer{
                displayInfo(userDataViewModel.getUserInfo())
            })
        }else{
            displayInfo(info)
        }
    }

    private fun displayInfo(userInfo: UserInfo?){
        login_value.text = userInfo?.printService?.login
        balance_value.text = userInfo?.printService?.balance.toString()
    }





}
