package com.karine.eventosapp.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karine.eventosapp.api.ApiService
import com.karine.eventosapp.api.model.Donation
import com.karine.eventosapp.database.dao.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userDao: UserDao,
    private val apiService: ApiService
) : ViewModel() {

    val user = userDao.getLoggedInUserFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            initialValue = null
        )

    private val _uiState: MutableStateFlow<List<Donation>> = MutableStateFlow(emptyList())
    val uiState = _uiState.asStateFlow()

    init {
        loadDonations()
    }

    fun loadDonations() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = apiService.getDonations()
                _uiState.value = request.data
                Log.d("TAG", "loadDonations: ${request.data}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.logout()
        }
    }

    fun deleteTransaction(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            apiService.deleteDonation(id)
            loadDonations()
        }
    }
}
