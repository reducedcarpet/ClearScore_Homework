package org.mcp.clearscorehomework.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mcp.clearscorehomework.models.CreditScoreModel
import org.mcp.clearscorehomework.services.APIService

class CreditScoreViewModel(application: Application) : AndroidViewModel(application) {

    val creditScore: MutableLiveData<CreditScoreModel?> = MutableLiveData()

    fun fetchCreditScore() {
        viewModelScope.launch(Dispatchers.IO) {
            APIService.callGetCreditScoreModel(creditScore)
        }
    }
}