package hollybits.ucursi.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import hollybits.ucursi.R
import hollybits.ucursi.login.LoginFragment
import hollybits.ucursi.splash.SplashFragment

class MainActivity : AppCompatActivity(),
    SplashFragment.SplashEvents,
    LoginFragment.LoginEvents{

    companion object {
        private const val TAG  = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun moveToLogin() {
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_user_must_login)
    }

    override fun userIsAlreadyLoggedIn() {
        Log.i(TAG, "userIsAlreadyLoggedIn")
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.user_authenticated)
    }
}
