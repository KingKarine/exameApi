package com.karine.eventosapp.api

import com.karine.eventosapp.api.model.CreateAccountRequest
import com.karine.eventosapp.api.model.LoginRequest
import com.karine.eventosapp.api.model.Donation
import com.karine.eventosapp.api.model.DonationRequest
import com.karine.eventosapp.api.model.Response
import com.karine.eventosapp.database.models.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): Response<User>

    @POST("user/register")
    suspend fun createAccount(@Body request: CreateAccountRequest)


    @GET("donations")
    suspend fun getDonations(): Response<List<Donation>>

    @POST("donations")
    suspend fun addDonation(@Body request: DonationRequest)

    @DELETE("donations/{id}")
    suspend fun deleteDonation(@Path("id") id: Int)

    @GET("donations/{id}")
    suspend fun getDonationById(@Path("id") id: Int): Response<Donation>

    @GET("donations/user/{userId}")
    suspend fun getUserDonation(@Path("userId") userId: Int): Response<List<Donation>>
}