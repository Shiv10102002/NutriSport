package org.shiv.data.domain

import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import org.shiv.shared.domain.Customer
import org.shiv.shared.util.RequestState

interface CustomerRepository {

    fun getCurrentUserId():String?

    suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess:()->Unit,
        onError:(String)->Unit
    )

    suspend fun signOut(): RequestState<Unit>

    fun readCustomerFlow(): Flow<RequestState<Customer>>

    suspend fun updateCustomer(
        customer: Customer,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )
}