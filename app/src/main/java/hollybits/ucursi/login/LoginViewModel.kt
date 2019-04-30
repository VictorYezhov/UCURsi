package hollybits.ucursi.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.util.Log
import hollybits.ucursi.R


class LoginViewModel(app: Application) : AndroidViewModel(app) {

    companion object {
        private const val TAG = "LoginViewModel"
    }
    private lateinit var googleSignInClient: GoogleSignInClient

    private val userLoginLiveData = MutableLiveData<GoogleSignInAccount>()
    private val errorLiveData = MutableLiveData<String>()


    fun isUserLoginned() = userLoginLiveData

    fun errorState() = errorLiveData


    fun initGoogleClient(googleSignInClient: GoogleSignInClient) {
        this.googleSignInClient = googleSignInClient
    }

    fun checkUser() {
        Log.i(TAG, "checkUser")
        val account = GoogleSignIn.getLastSignedInAccount(getApplication())
        account?.apply {
            Log.i(TAG, "checkUser, user is already logged in")
            userLoginLiveData.value = account // This means that user is already logged in with google credentials
        }
    }
    fun handleSignInResult(task : Task<GoogleSignInAccount>){
        try {
            val account = task.getResult(ApiException::class.java)
            Log.i(TAG, "handleSignInResult, success")
            Log.i(TAG, "handleSignInResult, user email : ${account?.email}")
            Log.i(TAG, "handleSignInResult, user name : ${account?.displayName}")
            Log.i(TAG, "handleSignInResult, token: ${account?.idToken}")

            // Signed in successfully, show authenticated UI.
            userLoginLiveData.value = account
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.i(TAG, "signInResult:failed code=" + e.statusCode)
            errorLiveData.value = (getApplication() as Context).resources.getString(R.string.sign_in_error)
        }

    }


}
