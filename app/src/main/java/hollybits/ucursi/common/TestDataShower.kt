package hollybits.ucursi.common


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import hollybits.ucursi.R
import hollybits.ucursi.main.AuthDataViewModel
import kotlinx.android.synthetic.main.fragment_test_data_shower.*
import android.os.AsyncTask.execute

import android.graphics.BitmapFactory
import android.graphics.Bitmap

import android.util.Log
import java.io.BufferedInputStream
import java.io.IOException
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class TestDataShower : Fragment() {

    private lateinit var authViewModel: AuthDataViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_data_shower, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authViewModel = ViewModelProviders.of(activity!!).get(AuthDataViewModel::class.java)


        name.text = authViewModel.getAuthenticatedUser()?.displayName
        email.text = authViewModel.getAuthenticatedUser()?.email
        family_name.text = authViewModel.getAuthenticatedUser()?.familyName
        var service = Executors.newSingleThreadScheduledExecutor()
        service.schedule({avater.setImageBitmap(getImageBitmap(authViewModel.getAuthenticatedUser()?.photoUrl?.toString()!!))}, 1, TimeUnit.SECONDS)


    }

    private fun getImageBitmap(url: String): Bitmap? {
        var bm: Bitmap? = null
        try {
            val aURL = URL(url)
            val conn = aURL.openConnection()
            conn.connect()
            val `is` = conn.getInputStream()
            val bis = BufferedInputStream(`is`)
            bm = BitmapFactory.decodeStream(bis)
            bis.close()
            `is`.close()
        } catch (e: IOException) {
            //Log.e(TAG, "Error getting bitmap", e)
        }

        return bm
    }


}
