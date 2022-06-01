package org.mcp.clearscorehomework.services

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.mcp.clearscorehomework.models.ClearScoreAPIInterface
import org.mcp.clearscorehomework.models.CreditScoreModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Generally shouldn't be static. Should use either a factory method or maybe some DI
object APIService {

    const val TAG = "APIService"

    private val apiInterface = ClearScoreAPIInterface.create().getCreditScore()

    fun callGetCreditScoreModel(creditScore: MutableLiveData<CreditScoreModel?>) {
        apiInterface.enqueue(
            object : Callback<CreditScoreModel> {
                override fun onResponse(
                    call: Call<CreditScoreModel>,
                    response: Response<CreditScoreModel>
                ) {
                    if (response.body() != null) {
                        creditScore.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<CreditScoreModel>, t: Throwable) {
                    // Logging goes here - probably with a larger library / cloud service
                    Log.e(TAG, "CreditScore get API endpoint failure.")
                }
            },
        )
    }
}