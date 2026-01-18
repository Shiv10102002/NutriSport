package org.shiv

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.shiv.data.domain.CustomerRepository
import org.shiv.shared.domain.Customer
import org.shiv.shared.util.RequestState

class CustomerRepositoryImpl : CustomerRepository {
    override fun getCurrentUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    override suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            if (user != null) {
                val customerCollection = Firebase.firestore.collection(collectionPath = "customer")
                val customer = Customer(
                    id = user.uid,
                    firstName = user.displayName?.split(" ")?.firstOrNull() ?: "Unknown",
                    lastName = user.displayName?.split(" ")?.lastOrNull() ?: "Unknown",
                    email = user.email ?: "Unknown",
                )

                val customerExists = customerCollection.document(user.uid).get().exists

                if (customerExists) {
                    onSuccess()
                } else {
                    customerCollection.document(user.uid).set(customer)
                }
            }else{
                onError("User is not available.")
            }
        }catch (e: Exception){
            onError("Error while creating a Customer: ${e.message}")
        }
    }

    override suspend fun signOut(): RequestState<Unit> {
        try {
            Firebase.auth.signOut()
            return RequestState.Success(data = Unit)
        }catch (e: Exception){
           return RequestState.Error("Error occurred while signOut: ${e.message}")
        }
    }
    override fun readCustomerFlow(): Flow<RequestState<Customer>> = channelFlow {
        try {
            val userId = getCurrentUserId()
            if (userId != null) {
                val database = Firebase.firestore
                database.collection(collectionPath = "customer")
                    .document(userId)
                    .snapshots
                    .collectLatest { document ->
                        if (document.exists) {
//                            val privateDataDocument =
//                                database.collection(collectionPath = "customer")
//                                    .document(userId)
//                                    .collection(collectionPath = "privateData")
//                                    .document("role")
//                                    .get()

                            val customer = Customer(
                                id = document.id,
                                firstName = document.get(field = "firstName"),
                                lastName = document.get(field = "lastName"),
                                email = document.get(field = "email"),
                                city = document.get(field = "city"),
                                postalCode = document.get(field = "postalCode"),
                                address = document.get(field = "address"),
                                phoneNumber = document.get(field = "phoneNumber"),
                                cart = document.get(field = "cart"),
//                                isAdmin = privateDataDocument.get(field = "isAdmin")
                            )
                            send(RequestState.Success(data = customer))
                        } else {
                            send(RequestState.Error("Queried customer document does not exist."))
                        }
                    }
            } else {
                send(RequestState.Error("User is not available."))
            }
        } catch (e: Exception) {
            send(RequestState.Error("Error while reading a Customer information: ${e.message}"))
        }
    }

    override suspend fun updateCustomer(
        customer: Customer,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        try {
            val userId = getCurrentUserId()
            if (userId != null) {
                val firestore = Firebase.firestore
                val customerCollection = firestore.collection(collectionPath = "customer")

                val existingCustomer = customerCollection
                    .document(customer.id)
                    .get()
                if (existingCustomer.exists) {
                    customerCollection
                        .document(customer.id)
                        .update(
                            "firstName" to customer.firstName,
                            "lastName" to customer.lastName,
                            "city" to customer.city,
                            "postalCode" to customer.postalCode,
                            "address" to customer.address,
                            "phoneNumber" to customer.phoneNumber
                        )
                    onSuccess()
                } else {
                    onError("Customer not found.")
                }
            } else {
                onError("User is not available.")
            }
        } catch (e: Exception) {
            onError("Error while updating a Customer information: ${e.message}")
        }
    }


}

