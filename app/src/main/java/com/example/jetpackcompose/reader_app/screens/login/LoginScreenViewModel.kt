package com.example.jetpackcompose.reader_app.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.reader_app.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    // val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit )

            = viewModelScope.launch{
        try {
            _loading.value = true
            delay(200)
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Log.d("FB", "signInWithEmailAndPassword: Yayayay! ${task.result.toString()}")
                        home()
                    }else {
                        Log.d("FB", "signInWithEmailAndPassword: ${task.exception.toString()}")
                    }


                }

        }catch (ex: Exception){
            Log.d("FB", "signInWithEmailAndPassword: ${ex.message}")
        }
        finally {
            _loading.value = false
        }


    }



    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //me
                        val displayName = task.result?.user?.email?.split('@')?.get(0)
                        createUser(displayName)
                        home()
                    }else {
                        Log.d("FB", "createUserWithEmailAndPassword: ${task.exception.toString()}")

                    }
                    _loading.value = false


                }
        }


    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid

        val user = User(userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Life is great",
            profession = "Android Developer",
            id = null).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }


}