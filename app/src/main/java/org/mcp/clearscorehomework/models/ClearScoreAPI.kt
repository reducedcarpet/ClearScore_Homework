package org.mcp.clearscorehomework.models

import org.mcp.clearscorehomework.data.baseURL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ClearScoreAPIInterface {

    @GET("endpoint.json")
    fun getCreditScore() : Call<CreditScoreModel>

    companion object {

        fun create() : ClearScoreAPIInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build()

            return retrofit.create(ClearScoreAPIInterface::class.java)
        }
    }

}