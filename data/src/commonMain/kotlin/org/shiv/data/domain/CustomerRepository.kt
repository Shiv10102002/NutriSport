package org.shiv.data.domain

import dev.gitlive.firebase.auth.FirebaseUser
import org.shiv.shared.util.RequestState

interface CustomerRepository {

    fun getCurrentUserId():String?

    suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess:()->Unit,
        onError:(String)->Unit
    )

    suspend fun signOut(): RequestState<Unit>
}