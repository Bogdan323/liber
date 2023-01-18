package com.example.liber.common.firebase

import com.example.liber.domain.usermodel.SavedGames
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseCommon(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    private val cartCollection = firestore.collection("user")
        .document(auth.uid!!)
        .collection("cart")

    fun addProductToCart(savedGames: SavedGames, onResult: (SavedGames?, Exception?) -> Unit) {
        cartCollection.document().set(savedGames)
            .addOnSuccessListener {
                onResult(savedGames, null)
            }
            .addOnFailureListener {
                onResult(null, it)
            }
    }

    fun increaseQuantity(documentId: String, onResult: (String?, Exception?) -> Unit) {
        firestore.runTransaction { transition ->
            val documentRef = cartCollection.document(documentId)
            val document = transition.get(documentRef)
            val productObject = document.toObject(SavedGames::class.java)
            productObject?.let { cartProduct ->
                val newQuantity = cartProduct.quantity + 1
                val newProductObject = cartProduct.copy(quantity = newQuantity)
                transition.set(documentRef, newProductObject)
            }
        }
        .addOnSuccessListener {
            onResult(documentId, null)
        }
        .addOnFailureListener {
            onResult(null, it)
        }
    }
}