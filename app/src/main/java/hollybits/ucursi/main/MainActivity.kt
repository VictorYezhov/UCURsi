package hollybits.ucursi.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import hollybits.ucursi.R
import hollybits.ucursi.login.LoginFragment
import hollybits.ucursi.services.MainMenuFragment
import hollybits.ucursi.splash.SplashFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    SplashFragment.SplashEvents,
    LoginFragment.LoginEvents,
    MainMenuFragment.MainMenuEvents{

    companion object {
        private const val TAG  = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        accountImageView.setOnClickListener {
            moveToAccount()
        }

        infoImageView.setOnClickListener {
            moveToInfoMenu()
        }
    }


    override fun moveToLogin() {
        Log.i(TAG, "moveToLogin")
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_user_must_login)
    }



    private fun moveToInfoMenu(){
        Log.i(TAG, "moveToInfoMenu")
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_mainMenuFragment_to_infoMenuFragment)
    }
    override fun moveToAccount() {
        Log.i(TAG, "moveToLogin")
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.personalAccount)
    }

    override fun moveToOrderDocument() {
        Log.i(TAG, "moveToOrderDocument")
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.orderDocumentFragment)
    }

    override fun userIsAlreadyLoggedIn() {
        Log.i(TAG, "userIsAlreadyLoggedIn")
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.user_authenticated)
    }
}
