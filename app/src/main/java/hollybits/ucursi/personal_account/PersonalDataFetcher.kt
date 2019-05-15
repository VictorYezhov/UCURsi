package hollybits.ucursi.personal_account

import android.util.Log
import hollybits.ucursi.personal_account.data.UserInfo
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


internal interface UCUApiClient {
    @POST("/api/student/")
    fun loadData(@Query("email") email:String,@Query("token") token :String): Call<UserInfo>
}



class PersonalDataFetcher {

    companion object {
        private const val URL = "https://grescode.com"
        private const val TAG = "PersonalDataFetcher"
    }

    private val mApiClient: UCUApiClient

    init {
        val interceptor = HttpLoggingInterceptor { message -> Log.i("Retrofit", message) }
        val utf8FormattingInterceptor = Interceptor {
            val response = it.proceed(it.request())
            val modified = response.newBuilder()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build()
            Log.i(TAG, "UTF-8 encoding set")

             modified
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(utf8FormattingInterceptor)
                    .addInterceptor(interceptor)
                    .build())
            .baseUrl(URL)
            .build()

        mApiClient = retrofit.create(UCUApiClient::class.java)
    }


    fun getPersonalData(email:String, token :String):Call<UserInfo>{
        return mApiClient.loadData(email, token)
    }


}