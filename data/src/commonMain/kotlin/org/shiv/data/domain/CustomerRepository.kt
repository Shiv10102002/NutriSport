package org.shiv.data.domain

import dev.gitlive.firebase.auth.FirebaseUser

interface CustomerRepository {

    fun getCurrentUserId():String?

    suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess:()->Unit,
        onError:(String)->Unit
    )
}