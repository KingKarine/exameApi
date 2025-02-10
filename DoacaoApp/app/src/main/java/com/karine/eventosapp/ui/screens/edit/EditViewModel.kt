package com.karine.eventosapp.ui.screens.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karine.eventosapp.api.ApiService
import com.karine.eventosapp.api.model.Donation
import com.karine.eventosapp.database.dao.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditViewModel(
    private val userDao: UserDao,
    private val apiService: ApiService,
    private val transactionId: Int
) : ViewModel() {

    private val _donationDetails = MutableStateFlow<Donation?>(null)
    val donationDetails: StateFlow<Donation?> = _donationDetails

    init {
        fetchNoteDetails()
    }

    fun fetchNoteDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}