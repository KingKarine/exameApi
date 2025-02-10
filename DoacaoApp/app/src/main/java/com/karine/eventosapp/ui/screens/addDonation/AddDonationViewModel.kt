package com.karine.eventosapp.ui.screens.addDonation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karine.eventosapp.api.ApiService
import com.karine.eventosapp.api.model.DonationRequest
import com.karine.eventosapp.database.dao.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddDonationViewModel(
    private val userDao: UserDao,
    private val apiService: ApiService
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTransaction(donationRequest: DonationRequest) {
        viewModelScope.launch (Dispatchers.IO){
            try {
                val user = userDao.getLoggedInUser()!!
                val req = donationRequest.copy(userId = user.id)
                apiService.addDonation(req)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}