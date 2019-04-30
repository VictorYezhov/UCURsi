package hollybits.ucursi.main

import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class AuthDataViewModel : ViewModel(){


    private  lateinit var userGoogleAccount : GoogleSignInAccount

    fun initAccount(userGoogleAccount : GoogleSignInAccount){
        this.userGoogleAccount = userGoogleAccount
    }

    fun getAuthenticatedUser():GoogleSignInAccount?{
        return  userGoogleAccount
    }

}