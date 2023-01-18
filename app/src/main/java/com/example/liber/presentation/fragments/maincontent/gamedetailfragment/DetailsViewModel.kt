package com.example.liber.presentation.fragments.maincontent.gamedetailfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liber.domain.usermodel.SavedGames
import com.example.liber.common.firebase.FirebaseCommon
import com.example.liber.common.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val firebaseCommon: FirebaseCommon
) : ViewModel() {

    private val _addToSavedGames = MutableStateFlow<Resource<SavedGames>>(Resource.Unspecified())
    val addToSavedGames = _addToSavedGames.asStateFlow()

    fun addUpdateProductInCard(savedGames: SavedGames) {
        viewModelScope.launch {
            _addToSavedGames.emit(Resource.Loading())
        }
        firestore.collection("user")
            .document(auth.uid!!)
            .collection("cart")
            .whereEqualTo("product.id", savedGames.product.id)
            .get()
            .addOnSuccessListener {
                it.documents.let {
                    if(it.isEmpty()){
                        addNewProduct(savedGames)
                    }else{
                        val product = it.first().toObject(SavedGames::class.java)
                        if (product == savedGames) { // Increase the quantity
                            val documentId = it.first().id
                            increaseQuantity(documentId, savedGames)
                        }else{
                            addNewProduct(savedGames)
                        }
                    }
                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    _addToSavedGames.emit(Resource.Error(it.message.toString()))
                }
            }
    }

    private fun addNewProduct(savedGames: SavedGames){
        firebaseCommon.addProductToCart(savedGames) { addedProduct, e ->
            viewModelScope.launch {
                if (e == null)
                    _addToSavedGames.emit(Resource.Success(addedProduct!!))
                else
                    _addToSavedGames.emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private fun increaseQuantity(documentId: String, savedGames: SavedGames){
        firebaseCommon.increaseQuantity(documentId) { _, e ->
            viewModelScope.launch {
                if (e == null)
                    _addToSavedGames.emit(Resource.Success(savedGames))
                else
                    _addToSavedGames.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}