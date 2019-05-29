package hollybits.ucursi.personal_account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import hollybits.ucursi.personal_account.data.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel : ViewModel() {

    companion object {
        private const val TAG = "AccountViewModel"
        private const val DEF_VAL = "Not Set"
    }
    private val dataFetcher = PersonalDataFetcher()

    private var userInfo : UserInfo? = null
    val userName = MutableLiveData<String>()
    val userFatherName = MutableLiveData<String>()
    val userPhone  = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()
    val usersGrop = MutableLiveData<String>()
    val errorIndicator = MutableLiveData<Boolean>()
    val isDataLoaded = MutableLiveData<Boolean>()



    fun loadAccountData(email:String, token:String){
        dataFetcher.getPersonalData(email, token).enqueue(object :Callback<UserInfo>{
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.i(TAG, "loadAccountData -> onFailure, ${t.message} ")
                errorIndicator.value = true
            }

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                Log.i(TAG, "loadAccountData -> onResponse, isSuccessful: ${response.isSuccessful} ")
                if(response.isSuccessful && response.body() != null){
                    userInfo = response.body()
                    if(userInfo != null) {
                        isDataLoaded.value = true
                        userName.value = userInfo!!.user.name + " " + userInfo!!.user.surname

                        if (userInfo!!.user.phone == null) {
                            userPhone.value = DEF_VAL
                        } else {
                            userPhone.value = userInfo!!.user.phone
                        }
                        userEmail.value = userInfo!!.user.email
                        usersGrop.value = userInfo!!.departament.departmentName
                        val displayName = userInfo!!.user.displayName
                        userFatherName.value = "Not Set"
                        displayName?.let {
                            userFatherName.value = it.split(" ")[2]
                        }
                    }

                }else{
                    Log.i(TAG, "loadAccountData -> onResponse,error ")
                    errorIndicator.value = true

                }

            }
        })

    }

    fun getUserInfo() = userInfo




}
