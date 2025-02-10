package com.karine.eventosapp.ui.screens.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karine.eventosapp.api.ApiService
import com.karine.eventosapp.api.model.CreateAccountRequest
import com.karine.eventosapp.api.model.LoginRequest
import com.karine.eventosapp.database.dao.UserDao
import com.karine.eventosapp.database.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userDao: UserDao,
    private val apiService: ApiService
): ViewModel() {

    val user = mutableStateOf<User?>(null)
    val error = mutableStateOf(false)
    val logged = mutableStateOf(false)

    val loggedUser = userDao.getLoggedInUserFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            null
        )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            user.value = userDao.getLoggedInUser()
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = apiService.login(LoginRequest(email, password))
                if (request.isSuccess) {
                    userDao.addUser(request.data)
                    logged.value = true
                } else {
                    error.value = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
                error.value = true
            }
        }
    }

    fun resetError() {
        error.value = false
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.logout()
        }
    }

    fun cadastrar(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
           try {
               apiService.createAccount(CreateAccountRequest(name, email, password))
           }  catch (e: Exception) {
               e.printStackTrace()
           }
        }
    }

}